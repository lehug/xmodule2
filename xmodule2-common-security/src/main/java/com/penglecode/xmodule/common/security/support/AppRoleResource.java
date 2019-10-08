package com.penglecode.xmodule.common.security.support;

import com.penglecode.xmodule.common.support.BaseModel;

/**
 * 应用-角色-资源关系
 * 
 * @author 	pengpeng
 * @date	2019年7月1日 上午11:37:09
 */
public class AppRoleResource implements BaseModel<AppRoleResource> {

	private static final long serialVersionUID = 1L;

	private Long appId;
	
	private String appName;
	
	private Long roleId;
	
	private String roleName;
	
	private String roleCode;
	
	private String resourceUrl;
	
	private String httpMethod;

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	
}
