package com.penglecode.xmodule.common.security.support;

/**
 * UPMS用户登录Form表单
 * 
 * @author 	pengpeng
 * @date	2019年6月24日 上午11:39:26
 */
public class UpmsLoginForm {

	private String userName;
	
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
