/**
 * 
 */
package com.cloudpaas.cache.keygen;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
import com.cloudpaas.cache.anno.CacheWrite;
import com.cloudpaas.cache.anno.IgnoreField;
import com.cloudpaas.common.utils.ReflectionUtils;
import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * @author 大鱼
 *
 * @date 2019年8月22日 下午3:14:56
 */
@Component
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
		if(anno.pkg().equals("true") && "".equals(anno.key())){
			key.append(buildKeyPrefix(anno))
			   .append(buildPkg(target,method))
			   .append("(").append(buildAllArgs(arguments,method))
			   .append(")");
			   
			/*
			if(null!=anno.key() && ""!=anno.key() && anno.key().length()>0){
				key.append("(");
				key.append(")");
				key.append(AbstractKeyGenerator.LINE).append(parserKey(anno.key(),parameterTypes,argNames,arguments));
				
			}else{
				key.append("(").append(buildAllArgs(arguments))
				   .append(")");
			}*/
			
			//.append(AbstractKeyGenerator.LINE).append(anno.key())
		}else if(anno.model().equals("true")){
			ParameterizedType pt = (ParameterizedType)target.getGenericSuperclass();
            Type returnType =pt.getActualTypeArguments()[0];
            String modelClassName = returnType.getTypeName();
            key.append(buildKeyPrefix(anno))
               .append(modelClassName).append(AbstractKeyGenerator.LINE)
               .append(parserKey(anno.key(),parameterTypes,argNames,arguments));
			
		}/*else if(anno.pkg().equals("true") && "".equals(anno.key())){
			key.append(buildKeyPrefix(anno))
			   .append(buildPkg(target,method))
			   .append("(").append(parserKey(anno.key(),parameterTypes,argNames,arguments))
			   .append(")");
		}*/else{
			key.append(buildKeyPrefix(anno))
			   .append(parserKey(anno.key(),parameterTypes,argNames,arguments));
		}
		
		return key.toString();
	}
	
	private String buildAllArgs(Object[] arguments,Method method){
		String str = "";
		StringBuffer sb =new StringBuffer();
		if(null!=arguments && arguments.length>0){
			int i = 0;
			for(Object arg : arguments){
				Parameter parameter = method.getParameters()[i];
				if(parameter.isAnnotationPresent(IgnoreField.class)){
				}else{
					sb.append(arg.toString()).append(AbstractKeyGenerator.LINE);
				}
				
				i++;
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
			Pattern pattern = Pattern.compile("\\#[a-zA-Z0-9]*(\\.)?[a-zA-Z0-9]*");
	        Matcher matcher = pattern.matcher(key);
	        while (matcher.find()) {
	        	String argName = matcher.group();
	        	String express[] = argName.split("\\.");
	        	String value = null;
	        	log.info("--------"+argName);
	        	
	        	//参数为对象类型
	        	if(express.length>1){
	        		String arg = express[0];
	        		String field = arg.replace("#", "");
	        		log.info("--------"+express[1]);
	        		Integer index = ArrayUtils.indexOf(argNames,field);
	        		log.info("--------"+index);
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
	
	private String buildKeyPrefix(CacheWrite anno){
		StringBuffer sb= new StringBuffer();
		if(null!=anno.prefix() && ""!=anno.prefix() && anno.prefix().length() > 0){
			sb.append(anno.prefix()).append(AbstractKeyGenerator.PRE_LINE);
		}
		/*
		if(null!=anno.key() && ""!=anno.key()){
			sb.append(anno.key()).append(AbstractKeyGenerator.LINE);
		}*/
		return sb.toString();
	}
	
	public static void main(String[] args){
		//String key = "'page_'+#params+'_'+#db+'_'+#id+'_'+#price";
		//String key = "'page_'+#t.id+'_'+#db+'_'+#id+'_'+#price";
		String key = "'.get()_get_'+#t.id";
		Pattern pattern = Pattern.compile("\\#[a-zA-Z0-9]*(\\.)?[a-zA-Z0-9]*");
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
        
        
        String[] v = {"",""}; 
        
	}

}
