/**
 * 
 */
package com.cloudpaas.admin.biz;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cloudpaas.admin.dao.AdminUserDao;
import com.cloudpaas.admin.model.JwtRequest;
import com.cloudpaas.common.config.JwtConfig;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.exception.UserInvalidException;
import com.cloudpaas.common.model.User;
import com.cloudpaas.common.utils.jwt.IJWTInfo;
import com.cloudpaas.common.utils.jwt.JWTHelper;
import com.cloudpaas.common.utils.jwt.JWTInfo;
/**
 * @author 大鱼
 *
 * @date 2019年8月16日 下午3:55:40
 */
@Service
public class AdminAuthBiz {
	

	private int expire = 2*60*60;

	@Autowired
	private AdminUserDao adminUserDao;
	@Autowired
	private JwtConfig jwtConfig;
	/**
	 * 登录时，根据密钥生成token
	 * @param jwtRequest
	 * @return
	 * @throws Exception
	 */
	public String login(JwtRequest jwtRequest) throws Exception{
		User entity = null;
		BeanUtils.copyProperties(jwtRequest, entity);
		User user = adminUserDao.selectOne(entity, CommonConstants.DEFAULT_DATASOURCE_KEY);
		if (!StringUtils.isEmpty(user.getId())) {
			JWTInfo jwtInfo = new JWTInfo(user.getUsername(),user.getId()+"",user.getName());
			return JWTHelper.generateToken(jwtInfo, jwtConfig.getUserPriKey(), expire);
		}
		throw new UserInvalidException("用户不存在或账户密码错误!");
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
