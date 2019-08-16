package com.cloudpaas.admin.ui.utils;

import java.net.URI;
import java.util.Map;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate 远程调用工具类
 * 
 * @author 大鱼
 *
 * @date 2019年8月14日 下午7:00:20
 */
public class RestTemplateUtils {

	private static final RestTemplate restTemplate = new RestTemplate();

	// ----------------------------------GET-------------------------------------------------------

	/**
	 * GET请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param responseType
	 *            返回对象类型
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> get(String url, Class<T> responseType) {
		return restTemplate.getForEntity(url, responseType);
	}

	/**
	 * GET请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> get(String url, Class<T> responseType, Object... uriVariables) {
		return restTemplate.getForEntity(url, responseType, uriVariables);
	}

	/**
	 * GET请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> get(String url, Class<T> responseType, Map<String, ?> uriVariables) {
		
		
		
		return restTemplate.getForEntity(url, responseType, uriVariables);
	}

	/**
	 * 带请求头的GET请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> get(String url, Map<String, String> headers, Class<T> responseType,
			Object... uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return get(url, httpHeaders, responseType, uriVariables);
	}

	/**
	 * 带请求头的GET请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> get(String url, HttpHeaders headers, Class<T> responseType,
			Object... uriVariables) {
		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		return exchange(url, HttpMethod.GET, requestEntity, responseType, uriVariables);
	}

	/**
	 * 带请求头的GET请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> get(String url, Map<String, String> headers, Class<T> responseType,
			Map<String, ?> uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return get(url, httpHeaders, responseType, uriVariables);
	}

	/**
	 * 带请求头的GET请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> get(String url, HttpHeaders headers, Class<T> responseType,
			Map<String, ?> uriVariables) {
		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		return exchange(url, HttpMethod.GET, requestEntity, responseType, uriVariables);
	}

	// ----------------------------------POST-------------------------------------------------------

	/**
	 * POST请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param responseType
	 *            返回对象类型
	 * @return
	 */
	public static <T> ResponseEntity<T> post(String url, Class<T> responseType) {
		return restTemplate.postForEntity(url, HttpEntity.EMPTY, responseType);
	}

	/**
	 * POST请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, Object requestBody, Class<T> responseType) {
		return restTemplate.postForEntity(url, requestBody, responseType);
	}

	/**
	 * POST请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, Object requestBody, Class<T> responseType,
			Object... uriVariables) {
		return restTemplate.postForEntity(url, requestBody, responseType, uriVariables);
	}

	/**
	 * POST请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, Object requestBody, Class<T> responseType,
			Map<String, ?> uriVariables) {
		return restTemplate.postForEntity(url, requestBody, responseType, uriVariables);
	}

	/**
	 * 带请求头的POST请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, Map<String, String> headers, Object requestBody,
			Class<T> responseType, Object... uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return post(url, httpHeaders, requestBody, responseType, uriVariables);
	}

	/**
	 * 带请求头的POST请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, HttpHeaders headers, Object requestBody, Class<T> responseType,
			Object... uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
		return post(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 带请求头的POST请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, Map<String, String> headers, Object requestBody,
			Class<T> responseType, Map<String, ?> uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return post(url, httpHeaders, requestBody, responseType, uriVariables);
	}

	/**
	 * 带请求头的POST请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, HttpHeaders headers, Object requestBody, Class<T> responseType,
			Map<String, ?> uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
		return post(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 自定义请求头和请求体的POST请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param requestEntity
	 *            请求头和请求体封装对象
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, HttpEntity<?> requestEntity, Class<T> responseType,
			Object... uriVariables) {
		return restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType, uriVariables);
	}

	/**
	 * 自定义请求头和请求体的POST请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param requestEntity
	 *            请求头和请求体封装对象
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> post(String url, HttpEntity<?> requestEntity, Class<T> responseType,
			Map<String, ?> uriVariables) {
		return restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType, uriVariables);
	}

	// ----------------------------------PUT-------------------------------------------------------

	/**
	 * PUT请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, Class<T> responseType, Object... uriVariables) {
		return put(url, HttpEntity.EMPTY, responseType, uriVariables);
	}

	/**
	 * PUT请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, Object requestBody, Class<T> responseType,
			Object... uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
		return put(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * PUT请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, Object requestBody, Class<T> responseType,
			Map<String, ?> uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
		return put(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 带请求头的PUT请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, Map<String, String> headers, Object requestBody,
			Class<T> responseType, Object... uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return put(url, httpHeaders, requestBody, responseType, uriVariables);
	}

	/**
	 * 带请求头的PUT请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, HttpHeaders headers, Object requestBody, Class<T> responseType,
			Object... uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
		return put(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 带请求头的PUT请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, Map<String, String> headers, Object requestBody,
			Class<T> responseType, Map<String, ?> uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return put(url, httpHeaders, requestBody, responseType, uriVariables);
	}

	/**
	 * 带请求头的PUT请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, HttpHeaders headers, Object requestBody, Class<T> responseType,
			Map<String, ?> uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
		return put(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 自定义请求头和请求体的PUT请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param requestEntity
	 *            请求头和请求体封装对象
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, HttpEntity<?> requestEntity, Class<T> responseType,
			Object... uriVariables) {
		return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, responseType, uriVariables);
	}

	/**
	 * 自定义请求头和请求体的PUT请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param requestEntity
	 *            请求头和请求体封装对象
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> put(String url, HttpEntity<?> requestEntity, Class<T> responseType,
			Map<String, ?> uriVariables) {
		return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, responseType, uriVariables);
	}

	// ----------------------------------DELETE-------------------------------------------------------

	/**
	 * DELETE请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, Class<T> responseType, Object... uriVariables) {
		return delete(url, HttpEntity.EMPTY, responseType, uriVariables);
	}

	/**
	 * DELETE请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, Class<T> responseType, Map<String, ?> uriVariables) {
		return delete(url, HttpEntity.EMPTY, responseType, uriVariables);
	}

	/**
	 * DELETE请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, Object requestBody, Class<T> responseType,
			Object... uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
		return delete(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * DELETE请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, Object requestBody, Class<T> responseType,
			Map<String, ?> uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
		return delete(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 带请求头的DELETE请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Class<T> responseType,
			Object... uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return delete(url, httpHeaders, responseType, uriVariables);
	}

	/**
	 * 带请求头的DELETE请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, HttpHeaders headers, Class<T> responseType,
			Object... uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
		return delete(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 带请求头的DELETE请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Class<T> responseType,
			Map<String, ?> uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return delete(url, httpHeaders, responseType, uriVariables);
	}

	/**
	 * 带请求头的DELETE请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, HttpHeaders headers, Class<T> responseType,
			Map<String, ?> uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
		return delete(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 带请求头的DELETE请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Object requestBody,
			Class<T> responseType, Object... uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return delete(url, httpHeaders, requestBody, responseType, uriVariables);
	}

	/**
	 * 带请求头的DELETE请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, HttpHeaders headers, Object requestBody,
			Class<T> responseType, Object... uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
		return delete(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 带请求头的DELETE请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Object requestBody,
			Class<T> responseType, Map<String, ?> uriVariables) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAll(headers);
		return delete(url, httpHeaders, requestBody, responseType, uriVariables);
	}

	/**
	 * 带请求头的DELETE请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param headers
	 *            请求头参数
	 * @param requestBody
	 *            请求参数体
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, HttpHeaders headers, Object requestBody,
			Class<T> responseType, Map<String, ?> uriVariables) {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
		return delete(url, requestEntity, responseType, uriVariables);
	}

	/**
	 * 自定义请求头和请求体的DELETE请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param requestEntity
	 *            请求头和请求体封装对象
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, HttpEntity<?> requestEntity, Class<T> responseType,
			Object... uriVariables) {
		return restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, responseType, uriVariables);
	}

	/**
	 * 自定义请求头和请求体的DELETE请求调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param requestEntity
	 *            请求头和请求体封装对象
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> delete(String url, HttpEntity<?> requestEntity, Class<T> responseType,
			Map<String, ?> uriVariables) {
		return restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, responseType, uriVariables);
	}

	// ----------------------------------通用方法-------------------------------------------------------

	/**
	 * 通用调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param method
	 *            请求方法类型
	 * @param requestEntity
	 *            请求头和请求体封装对象
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，按顺序依次对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
			Class<T> responseType, Object... uriVariables) {
		return restTemplate.exchange(url, method, requestEntity, responseType, uriVariables);
	}

	/**
	 * 通用调用方式
	 * 
	 * @param url
	 *            请求URL
	 * @param method
	 *            请求方法类型
	 * @param requestEntity
	 *            请求头和请求体封装对象
	 * @param responseType
	 *            返回对象类型
	 * @param uriVariables
	 *            URL中的变量，与Map中的key对应
	 * @return ResponseEntity 响应对象封装类
	 */
	public static <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
			Class<T> responseType, Map<String, ?> uriVariables) {
		
		return restTemplate.exchange(url, method, requestEntity, responseType, uriVariables);
	}
	
	/**
	 * 
	 * @param url
	 * @param method
	 * @param requestEntity
	 * @param reference
	 * @return
	 */
	public static <T> ResponseEntity<T>  exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
			ParameterizedTypeReference<T> reference) {
		return  getRestTemplate().exchange(url, method, requestEntity, reference);
	}
	/**
	 * 
	 * @param url
	 * @param method
	 * @param requestEntity
	 * @param reference
	 * @param uriVariables
	 * @return
	 */
	public static <T> ResponseEntity<T>  exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
			ParameterizedTypeReference<T> reference,Object... uriVariables) {
		return  getRestTemplate().exchange(url, method, requestEntity, reference,uriVariables);
	}
	
	public static <T> ResponseEntity<T>  exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
			ParameterizedTypeReference<T> reference,Map<String,?> uriVariables) {
		return  getRestTemplate().exchange(url, method, requestEntity, reference, uriVariables);
	}
	
	

	/**
	 * 获取RestTemplate实例对象，可自由调用其方法
	 * 
	 * @return RestTemplate实例对象
	 */
	public static RestTemplate getRestTemplate() {
		restTemplate.setRequestFactory(new HttpComponentsClientRestfulHttpRequestFactory());
		return restTemplate;
	}
	
	/**
	 * 解决httpMethod.get 无法requestBody问题
	 * @author 大鱼
	 *
	 * @date 2019年8月16日 下午1:43:54
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