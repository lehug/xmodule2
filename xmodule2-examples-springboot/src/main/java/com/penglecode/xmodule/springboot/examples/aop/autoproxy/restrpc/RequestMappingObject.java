package com.penglecode.xmodule.springboot.examples.aop.autoproxy.restrpc;

import org.springframework.web.bind.annotation.RequestMethod;

public class RequestMappingObject {

	private String path;
	
	private RequestMethod method;
	
	private String[] params;
	
	private String[] headers;
	
	private String[] consumes;
	
	private String[] produces;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public RequestMethod getMethod() {
		return method;
	}

	public void setMethod(RequestMethod method) {
		this.method = method;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public String[] getConsumes() {
		return consumes;
	}

	public void setConsumes(String[] consumes) {
		this.consumes = consumes;
	}

	public String[] getProduces() {
		return produces;
	}

	public void setProduces(String[] produces) {
		this.produces = produces;
	}
	
}
