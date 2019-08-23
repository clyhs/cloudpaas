/**
 * 
 */
package com.cloudpaas.cache.keygen;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cloudpaas.cache.anno.CacheClear;
import com.cloudpaas.cache.anno.CacheWrite;
import com.cloudpaas.common.utils.ReflectionUtils;

/**
 * 清除缓存生成key默认类
 * 
 * @author 大鱼
 *
 * @date 2019年8月22日 下午8:22:17
 * if key
 * prefix_key{*}
 * 
 * if keys
 * prefix_keys[0]{*},prefix_keys[1]{*}
 * 
 * if pkg=true
 * 
 * prefix_pkg_key+*
 * 
 */
@Component
public class DefaultKeyClearGenerator extends AbstractKeyClearGenerator {
	
	private static Logger log = LoggerFactory.getLogger(DefaultKeyClearGenerator.class);

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.keygen.AbstractKeyClearGenerator#buildKey(com.cloudpaas.cache.anno.CacheClear, java.lang.Class, java.lang.reflect.Method, java.lang.Class[], java.lang.String[], java.lang.Object[])
	 */
	@Override
	public String buildKey(CacheClear anno, Class<?> target, Method method, Class<?>[] parameterTypes,
			String[] argNames, Object[] arguments) {
		// TODO Auto-generated method stub
		StringBuffer key = new StringBuffer();
		if(anno.pkg().equals("true")){
			key.append(buildKeyPrefix(anno))
			   .append(buildPkg(target,method))
			   .append("*");
			/*
			if(null!=anno.key() && ""!=anno.key() && anno.key().length()>0){
				key.append(parserKey(anno.key(),parameterTypes,argNames,arguments));
				
			}else{
				key.append("*");
			}*/
			   
		}else if(anno.model().equals("true")){
			ParameterizedType pt = (ParameterizedType)target.getGenericSuperclass();
            Type returnType =pt.getActualTypeArguments()[0];
            String modelClassName = returnType.getTypeName();
            key.append(buildKeyPrefix(anno))
               .append(modelClassName).append(AbstractKeyGenerator.LINE)
               .append(parserKey(anno.key(),parameterTypes,argNames,arguments));
		}else{
			key.append(buildKeyPrefix(anno)).append(parserKey(anno.key(),parameterTypes,argNames,arguments));
		}
		
		return key.toString();
	}
	
	private String buildAllArgs(Object[] arguments){
		String str = "";
		StringBuffer sb =new StringBuffer();
		if(null!=arguments && arguments.length>0){
			for(Object arg : arguments){
				sb.append(arg.toString()).append(AbstractKeyGenerator.LINE);
			}
			str = sb.substring(0, sb.toString().length()-1);
		}
		
		
		return str;
	}
	
	private String buildPkg(Class<?> target,Method method){
		String packageName =target.getPackage().getName();
        String className = target.getSimpleName();  
        StringBuffer sb =new StringBuffer();
        sb.append(packageName).append(".").append(className);
        return sb.toString();
	}
	
	private String parserKey(String key,Class<?>[] parameterTypes,
			String[] argNames,
			Object[] arguments){
		String keyTmp = null;
		
		if(null!=key){
			keyTmp = key;
			keyTmp = keyTmp.replaceAll("\\'", "");
			keyTmp = keyTmp.replaceAll("\\+", "");
			Pattern pattern = Pattern.compile("\\#[a-zA-Z0-9]*(\\.)?[a-zA-Z0-9]*");
	        Matcher matcher = pattern.matcher(key);
	        while (matcher.find()) {
	        	String argName = matcher.group();
	        	String express[] = argName.split("\\.");
	        	String value = null;
	        	
	        	//参数为对象类型
	        	if(express.length>1){
	        		String arg = express[0];
	        		String field = arg.replace("#", "");
	        		Integer index = ArrayUtils.indexOf(argNames,field);
	        		if(null!=index){
	        			value =JSON.toJSONString(ReflectionUtils.getFieldValue(arguments[index], express[1]));
	        		}
	        		
	        	}else{
	        		//参数为普通字符串类型
	        		String arg = argName.replace("#", "");
	        		Integer index = ArrayUtils.indexOf(argNames,arg);
	
		        	if(null!=index){
		        		if (parameterTypes[index].isAssignableFrom(List.class)) {
		        			List result = (List) arguments[index];
		                    value = result.get(0).toString();
		        		}else{
		        			value = arguments[index].toString();
		        		}
		        	}
	        	}
	        	keyTmp = keyTmp.replace(argName, value);
	        	
	        }
		}
		
		return keyTmp;
	}
	
	private String buildKeyPrefix(CacheClear anno){
		StringBuffer sb= new StringBuffer();
		if(null!=anno.prefix() && ""!=anno.prefix() && anno.prefix().length() > 0){
			sb.append(anno.prefix()).append(AbstractKeyGenerator.PRE_LINE);
		}
		return sb.toString();
	}

}
