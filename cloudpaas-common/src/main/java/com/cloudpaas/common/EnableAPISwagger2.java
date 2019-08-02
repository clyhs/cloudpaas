package com.cloudpaas.common;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.cloudpaas.common.config.SwaggerConfiguration;

import java.lang.annotation.*;
/**
 * 
 * @author 大鱼
 *
 * @date 2019年8月2日 下午12:33:32
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SwaggerConfiguration.class)
@Documented
@Inherited
public @interface EnableAPISwagger2 {
}
