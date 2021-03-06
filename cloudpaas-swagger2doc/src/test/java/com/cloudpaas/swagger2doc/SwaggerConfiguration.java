package com.cloudpaas.swagger2doc;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
/**
 * 
 * @author 大鱼
 *
 * @date 2019年7月26日 上午10:13:09
 */

@Configuration
@ComponentScan({"com.cloudpaas"})
public class SwaggerConfiguration {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("服务提供者 API")
            .description("提供用户信息查询")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .build();
    }

    /**
     * 定义api配置.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
            .build()
            .apiInfo(apiInfo());
    }


}