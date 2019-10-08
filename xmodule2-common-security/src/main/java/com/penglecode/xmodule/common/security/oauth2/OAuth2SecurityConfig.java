package com.penglecode.xmodule.common.security.oauth2;

import java.util.HashSet;
import java.util.Set;

/**
 * OAuth2安全配置
 * 
 * @author 	pengpeng
 * @date	2019年6月21日 上午8:48:47
 */
public class OAuth2SecurityConfig {

	/**
	 * 不需要权限即可访问的URL列表
	 */
	private Set<String> permitAllUrls = new HashSet<String>();

	public Set<String> getPermitAllUrls() {
		return permitAllUrls;
	}

	public void setPermitAllUrls(Set<String> permitAllUrls) {
		this.permitAllUrls = permitAllUrls;
	}

}
