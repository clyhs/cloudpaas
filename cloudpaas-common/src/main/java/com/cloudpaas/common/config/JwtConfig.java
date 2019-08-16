/**
 * 
 */
package com.cloudpaas.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


import lombok.Data;

/**
 * @author 大鱼
 *
 * @date 2019年8月16日 下午4:23:20
 */
@Configuration
@Data
public class JwtConfig {
	
	@Value("${jwt.rsa-secret}")
    private String userRsaSecret;
	private byte[] userPubKey;
    private byte[] userPriKey;

}
