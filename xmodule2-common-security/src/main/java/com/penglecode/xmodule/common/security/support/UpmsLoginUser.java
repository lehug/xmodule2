package com.penglecode.xmodule.common.security.support;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.penglecode.xmodule.common.security.consts.SecurityApplicationConstants;
import com.penglecode.xmodule.common.util.CollectionUtils;
import com.penglecode.xmodule.platform.upms.consts.em.UpmsUserStatusEnum;
import com.penglecode.xmodule.platform.upms.model.UpmsUser;

/**
 * 基于spring-security的登录用户
 * 
 * @author 	pengpeng
 * @date	2019年1月16日 下午2:34:03
 */
public class UpmsLoginUser extends UpmsUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Map<Long,List<String>> userRoles;
    
    public UpmsLoginUser() {
		super();
	}
    
    public UpmsLoginUser(UpmsUser user) {
    	BeanUtils.copyProperties(user, this);
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
    	Collection<GrantedAuthority> authorities = new LinkedHashSet<>();
		if(!CollectionUtils.isEmpty(userRoles)) {
			for(Map.Entry<Long,List<String>> entry : userRoles.entrySet()) {
				Long appId = entry.getKey();
				List<String> roles = entry.getValue();
				if(!CollectionUtils.isEmpty(roles)) {
					for(String role : roles) {
						role = SecurityApplicationConstants.DEFAULT_ROLE_VOTE_PREFIX + role;
						GrantedAuthority authority = new MultiAppGrantedAuthority(role, appId);
						authorities.add(authority);
					}
				}
			}
		}
		return authorities;
	}

	@Override
	public String getUsername() {
		return getUserName();
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return UpmsUserStatusEnum.USER_STATUS_ENABLED.getStatusCode().equals(getStatus());
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Map<Long, List<String>> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Map<Long, List<String>> userRoles) {
		this.userRoles = userRoles;
	}

	public Map<String,Object> asMap() {
		Map<String,Object> data = new LinkedHashMap<String,Object>();
		data.put("userId", getUserId());
		data.put("userName", getUserName());
		data.put("realName", getRealName());
		data.put("nickName", getNickName());
		data.put("mobilePhone", getMobilePhone());
		data.put("email", getEmail());
		data.put("userIcon", getUserIcon());
		data.put("userIconUrl", getUserIconUrl());
		data.put("userType", getUserType());
		data.put("userTypeName", getUserTypeName());
		data.put("status", getStatus());
		data.put("statusName", getStatusName());
		data.put("lastLoginTime", getLastLoginTime());
		data.put("loginTimes", getLoginTimes());
		data.put("createTime", getCreateTime());
		data.put("createBy", getCreateBy());
		data.put("updateTime", getUpdateTime());
		data.put("updateBy", getUpdateBy());
		return data;
	}
	
	public String toString() {
		return asMap().toString();
	}
	
}
