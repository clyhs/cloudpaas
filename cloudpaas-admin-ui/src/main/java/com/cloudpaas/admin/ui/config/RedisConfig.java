package com.cloudpaas.admin.ui.config;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;

import redis.clients.jedis.JedisPoolConfig;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@Configuration
@EnableCaching // 开启缓存支持
public class RedisConfig extends CachingConfigurerSupport {

	@Resource
	private LettuceConnectionFactory lettuceConnectionFactory;

	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuffer sb = new StringBuffer();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					//由于参数可能不同, 缓存的key也需要不一样
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}
	
	

	// 缓存管理器
	@Bean
	public CacheManager cacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
		return new RedisCacheManager(
	            RedisCacheWriter.nonLockingRedisCacheWriter(lettuceConnectionFactory),
	            this.getRedisCacheConfigurationWithTtl(600), // 默认策略，未配置的 key 会使用这个
	            this.getRedisCacheConfigurationMap() // 指定 key 策略
	        );
	}
	/**
	 * 配置缓存过期时间，可以根据自己增加
	 * @return
	 */
	private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put("pageList", this.getRedisCacheConfigurationWithTtl(3000));
        redisCacheConfigurationMap.put("roleList", this.getRedisCacheConfigurationWithTtl(18000));

        return redisCacheConfigurationMap;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
            RedisSerializationContext
                .SerializationPair
                .fromSerializer(jackson2JsonRedisSerializer)
        ).entryTtl(Duration.ofSeconds(seconds));

        return redisCacheConfiguration;
    }

	/**
	 * RedisTemplate配置
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
		// 设置序列化
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
				Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
	    om.enableDefaultTyping(DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		// 配置redisTemplate
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(lettuceConnectionFactory);
		RedisSerializer<?> stringSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(stringSerializer);// key序列化
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);// value序列化
		redisTemplate.setHashKeySerializer(stringSerializer);// Hash key序列化
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);// Hash value序列化
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
	
	@Bean
	public JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(1000);
		jedisPoolConfig.setMaxIdle(200);
		jedisPoolConfig.setMaxWaitMillis(15000);
		jedisPoolConfig.setMinEvictableIdleTimeMillis(300000);
		jedisPoolConfig.setNumTestsPerEvictionRun(3);
		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(60000);
		jedisPoolConfig.setBlockWhenExhausted(true);
		
		return jedisPoolConfig;
	}
	
	

}