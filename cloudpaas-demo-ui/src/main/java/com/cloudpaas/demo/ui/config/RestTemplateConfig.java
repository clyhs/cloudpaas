package com.cloudpaas.demo.ui.config;

import com.cloudpaas.demo.ui.utils.DateTimeSerializer;
import com.cloudpaas.demo.ui.utils.DatetimeDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.collect.Lists;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * restTemplate配置类，json转换
 * 
 * @author 大鱼
 *
 * @date 2019年8月21日 下午6:48:18
 */
@Component
public class RestTemplateConfig {
	
	private static Logger log = LoggerFactory.getLogger(RestTemplateConfig.class);

    // 启动的时候要注意，由于我们在服务中注入了RestTemplate，所以启动的时候需要实例化该类的一个实例
    @Autowired
    private RestTemplateBuilder builder;

    @Autowired
    private ObjectMapper objectMapper;

    // 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
    @Bean
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = builder.build();

        //注册model,用于实现jackson joda time序列化和反序列化
        SimpleModule module = new SimpleModule();
        module.addSerializer(DateTime.class, new DateTimeSerializer());
        module.addDeserializer(DateTime.class, new DatetimeDeserializer());
        objectMapper.registerModule(module);

        List<HttpMessageConverter<?>> messageConverters = Lists.newArrayList();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);

        //不加会出现异常
        //Could not extract response: no suitable HttpMessageConverter found for response type [class ]
        MediaType[] mediaTypes = new MediaType[]{
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_OCTET_STREAM,

                MediaType.TEXT_HTML,
                MediaType.TEXT_PLAIN,
                MediaType.TEXT_XML,
                MediaType.APPLICATION_STREAM_JSON,
                MediaType.APPLICATION_ATOM_XML,
                MediaType.APPLICATION_FORM_URLENCODED,
                MediaType.APPLICATION_JSON_UTF8,
                MediaType.APPLICATION_PDF,
        };

        converter.setSupportedMediaTypes(Arrays.asList(mediaTypes));

        try {
            //通过反射设置MessageConverters
            Field field = restTemplate.getClass().getDeclaredField("messageConverters");

            field.setAccessible(true);

            List<HttpMessageConverter<?>> orgConverterList = (List<HttpMessageConverter<?>>) field.get(restTemplate);

            Optional<HttpMessageConverter<?>> opConverter = orgConverterList.stream()
                    .filter(x -> x.getClass().getName().equalsIgnoreCase(MappingJackson2HttpMessageConverter.class
                            .getName()))
                    .findFirst();

            if (opConverter.isPresent() == false) {
                return restTemplate;
            }

            messageConverters.add(converter);//添加MappingJackson2HttpMessageConverter

            //添加原有的剩余的HttpMessageConverter
            List<HttpMessageConverter<?>> leftConverters = orgConverterList.stream()
                    .filter(x -> x.getClass().getName().equalsIgnoreCase(MappingJackson2HttpMessageConverter.class
                            .getName()) == false)
                    .collect(Collectors.toList());

            messageConverters.addAll(leftConverters);

            log.info(String.format("【HttpMessageConverter】原有数量：%s，重新构造后数量：%s"
                    , orgConverterList.size(), messageConverters.size()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        restTemplate.setMessageConverters(messageConverters);
        
        restTemplate.setRequestFactory(new HttpComponentsClientRestfulHttpRequestFactory());

        return restTemplate;
    }
    
    /**
     * 解决exchange 在使用get请求时，无法使用@RequestBody传参问题
     * @author 大鱼
     *
     * @date 2019年8月21日 下午6:47:14
     */
    private static final class HttpComponentsClientRestfulHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {
	    @Override
	    protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
	        if (httpMethod == HttpMethod.GET) {
	            return new HttpGetRequestWithEntity(uri);
	        }
	        return super.createHttpUriRequest(httpMethod, uri);
	    }
	}

	private static final class HttpGetRequestWithEntity extends HttpEntityEnclosingRequestBase {
	    public HttpGetRequestWithEntity(final URI uri) {
	        super.setURI(uri);
	    }

	    @Override
	    public String getMethod() {
	        return HttpMethod.GET.name();
	    }
	}

}
