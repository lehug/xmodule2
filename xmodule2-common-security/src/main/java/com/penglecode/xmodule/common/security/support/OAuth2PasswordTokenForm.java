package com.penglecode.xmodule.common.security.support;

/**
 * OAuth2 password模式的token请求Form
 * 
 * @author 	pengpeng
 * @date	2019年7月2日 下午1:19:52
 */
public class OAuth2PasswordTokenForm {

	private final String grant_type = "password";
	
	private String scope;
	
	private String username;
	
	private String password;

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGrant_type() {
		return grant_type;
	}
	
}
