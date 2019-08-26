/**
 * 
 */
package com.cloudpaas.admin.biz;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cloudpaas.admin.model.JwtRequest;
import com.cloudpaas.admin.model.JwtResponse;
import com.cloudpaas.common.config.JwtConfig;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.exception.UserInvalidException;
import com.cloudpaas.common.jwt.IJWTInfo;
import com.cloudpaas.common.jwt.JWTInfo;
import com.cloudpaas.common.model.User;
import com.cloudpaas.common.utils.jwt.JWTHelper;
/**
 * 登录认证，生成token
 * 
 * @author 大鱼
 *
 * @date 2019年8月16日 下午3:55:40
 */
@Service
public class AdminAuthBiz {
	

	private int expire = 2*60*60;

	@Autowired
	private AdminUserBiz adminUserDao;
	@Autowired
	private JwtConfig jwtConfig;
	/**
	 * 登录时，根据密钥生成token
	 * @param jwtRequest
	 * @return
	 * @throws Exception
	 */
	public JwtResponse login(JwtRequest jwtRequest) throws Exception{
		User entity = new User();
		JwtResponse jwtResponse = new JwtResponse();
		BeanUtils.copyProperties(jwtRequest, entity);
		User user = adminUserDao.selectOne(entity, CommonConstants.DEFAULT_DATASOURCE_KEY);
		if (!StringUtils.isEmpty(user.getId())) {
			JWTInfo jwtInfo = new JWTInfo(user.getUsername(),user.getId()+"",user.getName());
			
			String token = JWTHelper.generateToken(jwtInfo, jwtConfig.getUserPriKey(), expire);
			jwtResponse.setToken(token);
			jwtResponse.setUser(user);
			return jwtResponse;
			
		}
		throw new UserInvalidException("用户不存在或账户密码错误!");
	}
	
	public JWTInfo getInfofromToken(String token) throws Exception {
		return (JWTInfo) JWTHelper.getInfoFromToken(token, jwtConfig.getUserPubKey());
	}
	
	/**
	 * 根据公钥解析token
	 * @param token
	 * @throws Exception
	 */
	public void validate(String token) throws Exception {
		JWTHelper.getInfoFromToken(token, jwtConfig.getUserPubKey());
    }
	/**
	 * 根据老的token刷新，生成新的token
	 * @param oldToken
	 * @return
	 * @throws Exception
	 */
	public String refresh(String oldToken) throws Exception {
		IJWTInfo jwtInfo =  JWTHelper.getInfoFromToken(oldToken, jwtConfig.getUserPubKey());
		return JWTHelper.generateToken(jwtInfo, jwtConfig.getUserPriKey(),expire);
        
    }
}
