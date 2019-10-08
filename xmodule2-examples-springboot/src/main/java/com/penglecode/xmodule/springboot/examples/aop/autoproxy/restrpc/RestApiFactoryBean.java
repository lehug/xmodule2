package com.penglecode.xmodule.springboot.examples.aop.autoproxy.restrpc;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;

public class RestApiFactoryBean implements FactoryBean<Object> {

	private final Class<?> restApiInterface;
	
	private Object restApiProxy;
	
	public RestApiFactoryBean(Class<?> restApiInterface) {
		super();
		this.restApiInterface = restApiInterface;
		this.restApiProxy = createRestApiProxy();
	}
	
	protected Object createRestApiProxy() {
		return new ProxyFactory(restApiInterface, new RestApiInvokeInterceptor()).getProxy();
	}

	@Override
	public Object getObject() throws Exception {
		return restApiProxy;
	}

	@Override
	public Class<?> getObjectType() {
		return restApiInterface;
	}

}
