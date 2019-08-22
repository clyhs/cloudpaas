/**
 * 
 */
package com.cloudpaas.cache.keygen;

import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudpaas.cache.anno.CacheWrite;

/**
 * @author 大鱼
 *
 * @date 2019年8月22日 下午3:14:56
 */
public class DefaultKeyGenerator extends AbstractKeyGenerator {
	
	private static Logger log = LoggerFactory.getLogger(DefaultKeyGenerator.class);

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.keygen.AbstractKeyGenerator#buildKey(java.lang.String, java.lang.Class[], java.lang.Object[])
	 */
	@Override
	public String buildKey(CacheWrite anno,Class<?> target,Method method, Class<?>[] parameterTypes,
			String[] argNames,
			Object[] arguments) {
		// TODO Auto-generated method stub
		StringBuffer key = new StringBuffer();
		if(anno.pkg().equals("true")){
			key.append(buildKeyPrefix(anno))
			   .append(buildPkg(target,method))
			   .append("(").append(buildAllArgs(arguments))
			   .append(")");
		}else{
			key.append(buildKeyPrefix(anno))
			   .append(parserKey(anno.key(),parameterTypes,argNames,arguments));
		}
		
		return key.toString();
	}
	
	private String buildAllArgs(Object[] arguments){
		StringBuffer sb =new StringBuffer();
		for(Object arg : arguments){
			sb.append(arg.toString()).append(AbstractKeyGenerator.LINE);
		}
		String str = sb.substring(0, sb.toString().length()-1);
		return str;
	}
	
	private String buildPkg(Class<?> target,Method method){
		String packageName =target.getPackage().getName();
        String className = target.getClass().getSimpleName();  
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
	
	private String buildKeyPrefix(CacheWrite anno){
		StringBuffer sb= new StringBuffer();
		if(null!=anno.prefix() && ""!=anno.prefix() && anno.prefix().length() > 0){
			sb.append(anno.prefix()).append(AbstractKeyGenerator.LINE);
		}
		/*
		if(null!=anno.key() && ""!=anno.key()){
			sb.append(anno.key()).append(AbstractKeyGenerator.LINE);
		}*/
		return sb.toString();
	}
	
	public static void main(String[] args){
		String key = "'page_'+#params+'_'+#db+'_'+#id+'_'+#price";
		Pattern pattern = Pattern.compile("\\#[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(key);
        key = key.replaceAll("\\'", "");
        key = key.replaceAll("\\+", "");
        System.out.println(key);
        int i = 0;
        while (matcher.find()) {
        	String temp = matcher.group();
        	String arg = temp.replace("#", "");
        	System.out.println(arg);
        	String value = i+++"";
        	key = key.replace(temp, value);
        	
        }
        System.out.println(key);
        
	}

}
