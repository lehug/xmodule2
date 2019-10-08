package com.penglecode.xmodule.springboot.examples.aop.autoproxy.restrpc;

import java.util.Map;

public class RequestParameterObject {

	private Map<String,Object> pathParams;
	
	private Map<String,Object> requestParams;
	
	private Object requestBody;

	public Map<String, Object> getPathParams() {
		return pathParams;
	}

	public void setPathParams(Map<String, Object> pathParams) {
		this.pathParams = pathParams;
	}

	public Map<String, Object> getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(Map<String, Object> requestParams) {
		this.requestParams = requestParams;
	}

	public Object getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}
	
}
