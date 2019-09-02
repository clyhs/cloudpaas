/**
 * 
 */
package com.cloudpaas.admin.runner;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import com.cloudpaas.common.config.JwtConfig;
import com.cloudpaas.common.utils.jwt.RsaKeyHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 启动时根据 jwtConfig的密码生成用户公钥，用户密钥
 * 
 * @author 大鱼
 *
 * @date 2019年8月16日 下午4:29:05
 * 
 * 可参考 com.cloudpaas.common.utils.jwt.RasDemo
 */
@Slf4j
@Configuration
public class AuthRunner implements CommandLineRunner {
	
	@Autowired
	RedisTemplate<String,String> redisTemplate;
	
	@Autowired
	JwtConfig jwtConfig;
	
	private static final String REDIS_USER_PRI_KEY = "AUTH:JWT:PRI";
    private static final String REDIS_USER_PUB_KEY = "AUTH:JWT:PUB";

	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
		if (redisTemplate.hasKey(REDIS_USER_PRI_KEY)&&redisTemplate.hasKey(REDIS_USER_PUB_KEY)) {
			
			jwtConfig.setUserPriKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_USER_PRI_KEY).toString()));
			jwtConfig.setUserPubKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_USER_PUB_KEY).toString()));
               
		} else {
            Map<String, byte[]> keyMap = RsaKeyHelper.generateKey(jwtConfig.getUserRsaSecret());
            jwtConfig.setUserPriKey(keyMap.get("pri"));
            jwtConfig.setUserPubKey(keyMap.get("pub"));
            redisTemplate.opsForValue().set(REDIS_USER_PRI_KEY, RsaKeyHelper.toHexString(keyMap.get("pri")));
            redisTemplate.opsForValue().set(REDIS_USER_PUB_KEY, RsaKeyHelper.toHexString(keyMap.get("pub")));
            
        }
	}

}
