package com.penglecode.xmodule.common.security.support;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

/**
 * 多应用GrantedAuthority
 * 
 * @author 	pengpeng
 * @date	2019年5月23日 下午12:21:39
 */
public class MultiAppGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private final String role;
	
	private final Long appId;
	
	public MultiAppGrantedAuthority(String role, Long appId) {
		super();
		Assert.hasText(role, "Parameter 'role' must be required!");
		Assert.notNull(appId, "Parameter 'appId' must be required!");
		this.role = role;
		this.appId = appId;
	}

	@Override
	public String getAuthority() {
		return role;
	}

	public Long getApplication() {
		return appId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appId == null) ? 0 : appId.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MultiAppGrantedAuthority other = (MultiAppGrantedAuthority) obj;
		if (appId == null) {
			if (other.appId != null)
				return false;
		} else if (!appId.equals(other.appId))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return role + "@" + appId;
	}

}
