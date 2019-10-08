package com.penglecode.xmodule.springboot.examples.aop.autoproxy.restrpc;

public class ApiResult<T> {

	private int code;
	
	private String message;
	
	private T result;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
}
