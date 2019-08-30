package com.cloudpaas.swagger2doc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.robwin.swagger2markup.Language;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureRestDocs(outputDir = "build/asciidoc/snippets")
@SpringBootTest(classes = {SwaggerApplication.class, SwaggerConfiguration.class})
@AutoConfigureMockMvc
public class Swagger2MarkupTest {

    private static final Logger LOG = LoggerFactory.getLogger(Swagger2MarkupTest.class);


    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext wac;

    /**
     * 初始化 MOCK
     */
    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


 

    @Test
    public void createSpringfoxSwaggerJson() throws Exception {
        //String designFirstSwaggerLocation = Swagger2MarkupTest.class.getResource("/swagger.yaml").getPath();

    	/*
        String outputDir = System.getProperty("io.springfox.staticdocs.outputDir");
        MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:8200/api/v2/api-docs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();*/
    	String outputDir = System.getProperty("io.springfox.staticdocs.outputDir");
    	
    	String url = "http://localhost:8200/api/v2/api-docs";
        


        //MockHttpServletResponse response = mvcResult.getResponse();
        //String swaggerJson = response.getContentAsString();
    	String swaggerJson = sendGet(url);
        Files.createDirectories(Paths.get(outputDir));
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputDir, "swagger.json"), StandardCharsets.UTF_8)){
            writer.write(swaggerJson);
        }
        
        
    }
    
    @SuppressWarnings("deprecation")
	public String sendGet(String url) {
    	//logger.info("发送请求的地址为：{}", url);
    	
		HttpClient httpClient = new HttpClient();
		GetMethod httpGet = new GetMethod(url);
		httpClient.setConnectionTimeout(3000);
		String responseMessage = "";
		
		try {
			int status = httpClient.executeMethod(httpGet);
			//logger.info("请求发送后的返回值：{}", status);
			
			if (status == 200) {
				BufferedInputStream bis = new BufferedInputStream(httpGet.getResponseBodyAsStream());
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] bytes = new byte[1024];
				
				int count = 0;
				while ((count = bis.read(bytes)) != -1) {
					bos.write(bytes, 0, count);
				}
				
				byte[] strByte = bos.toByteArray();
				responseMessage = new String(strByte, 0, strByte.length, "utf-8");
				bos.close();
				bis.close();
			}

		} catch (Exception e) {
			//logger.error("发送get请求出现异常，错误信息为：{}", e);
		}
		
		httpGet.releaseConnection();
		//logger.info("get请求返回的数据：{}", responseMessage);
		return responseMessage;
	}

    
}