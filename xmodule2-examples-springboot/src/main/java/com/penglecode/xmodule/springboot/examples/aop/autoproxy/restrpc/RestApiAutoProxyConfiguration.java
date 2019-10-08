package com.penglecode.xmodule.springboot.examples.aop.autoproxy.restrpc;

import java.util.Collections;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动生成@RestApi注解的基于HTTP协议的远程RPC代理
 * 
 * @author 	pengpeng
 * @date	2019年9月29日 上午10:50:04
 */
@Configuration
@ConditionalOnProperty(prefix="springboot.examples", name="active", havingValue="aop.autoproxy.restrpc", matchIfMissing=false)
public class RestApiAutoProxyConfiguration {

	@Bean
	public RestApiAutoConfigurer restApiAutoConfigurer() {
		RestApiAutoConfigurer restApiAutoConfigurer = new RestApiAutoConfigurer();
		restApiAutoConfigurer.setBasePackages(Collections.singleton("com.penglecode.xmodule.springboot.examples"));
		return restApiAutoConfigurer;
	}
	
	@Bean
	public RestApiAutoProxyExample autoProxyExample() {
		return new RestApiAutoProxyExample();
	}
	
}
