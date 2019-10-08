package com.penglecode.xmodule.springboot.examples.aop.autoproxy.restrpc;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class Test {

	public static void test1() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.apiopen.top/getJoke?type={type}&page={page}&count={count}";
		
		Class<?> clazz = JokeServiceRestApi.class;
		Method method = clazz.getMethod("getJokeList", new Class[] {String.class, Integer.class, Integer.class});
		Type returnType = method.getGenericReturnType();
		
		ResponseEntity<ApiResult<List<Joke>>> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, ParameterizedTypeReference.forType(returnType), "video", "1", "20");
		ApiResult<List<Joke>> result = response.getBody();
		System.out.println(result);
	}
	
	public static void test2() throws Exception {
		Class<?> clazz = JokeServiceRestApi.class;
		Method method = clazz.getMethod("getJokeList", new Class[] {String.class, Integer.class, Integer.class});
		RequestMapping classRequestMapping = AnnotatedElementUtils.findMergedAnnotation(clazz, RequestMapping.class);
		System.out.println(classRequestMapping);
		RequestMapping methodRequestMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
		System.out.println(methodRequestMapping);
		System.out.println(method.getGenericReturnType());
	}
	
	public static void test3() throws Exception {
		String uri = "https://api.apiopen.top/{category}/getJoke?type=video";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri);
		builder.query("page={page}");
		builder.query("count={count}");
		
		Map<String,Object> uriVariables = new HashMap<String,Object>();
		uriVariables.put("page", 1);
		uriVariables.put("count", "20");
		UriComponents uriComponents = builder.build();
		System.out.println(uriComponents.toString());
		System.out.println(uriComponents.toUriString());
	}
	
	public static void main(String[] args) throws Exception {
		test1();
		//test2();
		//test3();
	}

}
