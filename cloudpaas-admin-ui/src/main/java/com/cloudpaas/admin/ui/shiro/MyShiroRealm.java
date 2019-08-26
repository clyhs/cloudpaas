/**
 * 
 */
package com.cloudpaas.admin.ui.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloudpaas.admin.ui.system.biz.AuthBiz;
import com.cloudpaas.admin.ui.system.biz.UserBiz;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.jwt.JwtResponse;
import com.cloudpaas.common.model.Role;
import com.cloudpaas.common.model.User;

/**
 * @author 大鱼
 *
 * @date 2019年8月15日 下午4:44:30
 */
public class MyShiroRealm extends AuthorizingRealm {
	
	private static Logger log = LoggerFactory.getLogger(MyShiroRealm.class);
	
	@Autowired
	UserBiz userBiz;
	@Autowired
	AuthBiz authBiz;

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user  = (User)principals.getPrimaryPrincipal();
//        for(Role role:user.getRoleList()){
//            authorizationInfo.addRole(role.getRole());
//            for(SysPermission p:role.getPermissions()){
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
        return authorizationInfo;
	}

	/*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken AuthToken) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		UsernamePasswordToken token = (UsernamePasswordToken) AuthToken;

        //获取用户的输入的账号.
        String username = token.getUsername().trim();
       
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        
        String password = "";
        if (token.getPassword() != null) {
            password = new String(token.getPassword());
        }
        
        log.info("-----username:{},password:{}-----", username, password);
        
        //User user = userBiz.findUser(username);
        JwtResponse jwtResp = authBiz.login(username);
        User user = jwtResp.getUser();
        
        // 认证信息token里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名
        // String salt = user.getSalt();
      	// 盐也放进去
        // 这样通过配置中的 HashedCredentialsMatcher 进行自动校验
        
        //从数据库取了user.getPassword()与getName()作对比
        
        if(user != null){
        	SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    user, //
                    user.getPassword(), //密码
                    ByteSource.Util.bytes(user.getSalt()),
                    getName()  //realm name
            );
        	Session session = SecurityUtils.getSubject().getSession();
        	session.setAttribute(CommonConstants.USER_SESSION_ID, user);
        	if(null!= jwtResp.getToken()){
        		session.setAttribute(CommonConstants.USER_TOKEN, jwtResp.getToken());
        	}
        	
        	return authenticationInfo;
        }
        
        return null;
	}

}
