/**
 * 
 */
package com.cloudpaas.admin.ui.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisClusterManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSentinelManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.apache.shiro.mgt.SecurityManager;


import com.cloudpaas.admin.ui.shiro.MyShiroRealm;

/**
 * @author 大鱼
 *
 * @date 2019年8月15日 下午3:50:55
 */
@Configuration
//@EnableConfigurationProperties(RedisProperties.class)
public class ShiroConfig {
	
	private static Logger log = LoggerFactory.getLogger(ShiroConfig.class);
	
	@Value("${spring.profiles.active}")
	private String active;
	
	@Autowired
	RedisProperties redisProperties;
	
	@Bean
	public IRedisManager redisManager(){
		IRedisManager iManager = null;
		String host = null;
		if(active.equals("single")){
			log.debug("--------------single-----------------");
			//单机模式
			RedisManager redisManager = new RedisManager();
			redisManager.setHost(redisProperties.getHost()+":"+redisProperties.getPort());
			redisManager.setTimeout(10000);
			iManager = redisManager;
		}else if(active.equals("cluster")){
			log.debug("--------------cluster-----------------");
			//集群模式
			List<String> nodes = redisProperties.getCluster().getNodes();
			if(null!=nodes && nodes.size()>0){
				host = listToString(nodes,',');
			}
			RedisClusterManager redisManager =new RedisClusterManager();
			redisManager.setHost(host);
			redisManager.setTimeout(10000);
			iManager = redisManager;
		}else if(active.equals("sentinel")){
			log.debug("--------------sentinel-----------------");
			RedisSentinelManager redisManager =new RedisSentinelManager();
			List<String> nodes = redisProperties.getSentinel().getNodes();
			if(null!=nodes && nodes.size()>0){
				host = listToString(nodes,',');
			}
			redisManager.setHost(host);
			redisManager.setTimeout(10000);
			iManager = redisManager;
		}
		return iManager;
	}
	
	
	
	private String listToString(List<String> list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
	
	@Bean
	public RedisCacheManager redisCacheManager(){
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}
	
	@Bean(name="shiroFilter")
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//拦截器.
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		// 配置不会被拦截的链接 顺序判断
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/kaptcha", "anon");
		filterChainDefinitionMap.put("/login.json", "anon");
		filterChainDefinitionMap.put("/redis**", "anon");
		filterChainDefinitionMap.put("/loginout.html", "anon");
		filterChainDefinitionMap.put("/home/**", "anon");
		filterChainDefinitionMap.put("/index.html", "anon");
		
		//<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		//<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", "authc");
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login.html");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/main/index.html");

		//未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		//配置 退出过滤器
		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl("/login.html");
		Map<String,Filter> logout = new HashMap<>();
		logout.put("logout", logoutFilter);
		
		shiroFilterFactoryBean.setFilters(logout);
		return shiroFilterFactoryBean;
	}
	
	/**
	 * 凭证匹配器
	 * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
	 * ）
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
		return hashedCredentialsMatcher;
	}
	
	@Bean
	public MyShiroRealm myShiroRealm(){
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}
	
	/**
	 * 将session存入到redis中
	 * @return
	 */
	@Bean
	public RedisSessionDAO redisSessionDao(){
		RedisSessionDAO redisSessionDao = new RedisSessionDAO();
		redisSessionDao.setRedisManager(redisManager());

		redisSessionDao.setSessionInMemoryEnabled(false);
		return redisSessionDao;
	}
	/**
	 * sessionIdCookie的实现,用于重写覆盖容器默认的JSESSIONID
	 * @return
	 */
	@Bean
	public SimpleCookie simpleCookie(){
		SimpleCookie simpleCookie = new SimpleCookie("SHAREJSESSIONID");
		simpleCookie.setPath("/");
		simpleCookie.setHttpOnly(true);
		return simpleCookie;
	}
	
	/**
	 * session管理器
	 * @return
	 */
	@Bean
	public DefaultWebSessionManager sessionManager(){
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(1800000);
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionValidationInterval(60000);
		sessionManager.setSessionDAO(redisSessionDao());
		sessionManager.setSessionIdCookie(simpleCookie());
		sessionManager.setSessionValidationSchedulerEnabled(true);
		return sessionManager;
	}
	
	/**
	 * securityManager安全管理器
	 * @return
	 */
	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		securityManager.setCacheManager(redisCacheManager());
		securityManager.setSessionManager(sessionManager());
		return securityManager;
		
	}

	/**
	 *  开启shiro aop注解支持.
	 *  使用代理方式;所以需要开启代码支持;
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
	
	@Bean(name="simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver
	createSimpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
		mappings.setProperty("UnauthorizedException","403");
		r.setExceptionMappings(mappings);  // None by default
		r.setDefaultErrorView("error");    // No default
		r.setExceptionAttribute("ex");     // Default is "exception"
		//r.setWarnLogCategory("example.MvcLogger");     // No default
		return r;
	}
}
