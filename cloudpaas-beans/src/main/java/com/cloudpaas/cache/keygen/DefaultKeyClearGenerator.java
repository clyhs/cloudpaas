/**
 * 
 */
package com.cloudpaas.cache.keygen;

import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;

import com.cloudpaas.cache.anno.CacheClear;

/**
 * 清除缓存生成key默认类
 * 
 * @author 大鱼
 *
 * @date 2019年8月22日 下午8:22:17
 * 
 * prefix_key
 * 
 */
public class DefaultKeyClearGenerator extends AbstractKeyClearGenerator {

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.keygen.AbstractKeyClearGenerator#buildKey(com.cloudpaas.cache.anno.CacheClear, java.lang.Class, java.lang.reflect.Method, java.lang.Class[], java.lang.String[], java.lang.Object[])
	 */
	@Override
	public String buildKey(CacheClear anno, Class<?> target, Method method, Class<?>[] parameterTypes,
			String[] argNames, Object[] arguments) {
		// TODO Auto-generated method stub
		return null;
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
        sb.append(packageName).append(".").append(className).append(".").append(method.getName());
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
			Pattern pattern = Pattern.compile("\\#[a-zA-Z0-9]*");
	        Matcher matcher = pattern.matcher(key);
	        while (matcher.find()) {
	        	String argName = matcher.group();
	        	String arg = argName.replace("#", "");
	        	Integer index = ArrayUtils.indexOf(argNames,arg);
	        	String value = null;
	        	if(null!=index){
	        		if (parameterTypes[index].isAssignableFrom(List.class)) {
	        			List result = (List) arguments[index];
	                    value = result.get(0).toString();
	        		}else{
	        			value = arguments[index].toString();
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
			sb.append(anno.prefix()).append(AbstractKeyClearGenerator.LINE);
		}
		return sb.toString();
	}

}
