package com.cloudpaas.common.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * @author 大鱼
 *
 * @date 2019年8月7日 上午9:23:41
 */
public class JSONUtil {
	
	private static Logger log = LoggerFactory.getLogger(JSONUtil.class);
	
    private static ObjectMapper objectMapper=new ObjectMapper();
    public static String  toJson(Object obj1){
        try {
            return objectMapper.writeValueAsString(obj1);
        } catch (JsonProcessingException e) {
        	log.error(e.getMessage());
        }
        return null;
    }

    public static Object parseObject(String jsonStr,Class targetClass){
        try {
           return   objectMapper.readValue(jsonStr,targetClass);
        } catch (IOException e) {
            System.out.println("json转换异常");
        }
        return null;
    }

}

