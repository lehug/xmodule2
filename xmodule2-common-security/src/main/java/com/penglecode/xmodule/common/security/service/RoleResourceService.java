package com.penglecode.xmodule.common.security.service;

import java.util.List;

import com.penglecode.xmodule.common.security.support.AppRoleResource;

/**
 * 获取角色-资源关系列表
 * 
 * @author 	pengpeng
 * @date	2018年10月30日 下午4:12:18
 */
public interface RoleResourceService {

	/**
	 * 获取所有角色-资源权限关系
	 * @param appId		- 可选
	 * @return
	 */
	public List<AppRoleResource> getAllRoleResourceMappings(Long appId);
	
}
