package com.penglecode.xmodule.common.security.oauth2;

import org.springframework.beans.factory.InitializingBean;

/**
 * OAuth2认证服务器配置
 * 
 * @author 	pengpeng
 * @date	2019年2月19日 下午2:37:40
 */
public class OAuth2ServerConfigProperties implements InitializingBean {

	/** 所有资源客户端 */
	private OAuth2Client[] clients = {};
	
	/** 安全配置 */
    private OAuth2SecurityConfig security;
	
	public OAuth2Client[] getClients() {
		return clients;
	}

	public void setClients(OAuth2Client[] clients) {
		this.clients = clients;
	}

	public OAuth2SecurityConfig getSecurity() {
		return security;
	}

	public void setSecurity(OAuth2SecurityConfig security) {
		this.security = security;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if(security == null) {
			security = new OAuth2SecurityConfig();
		}
		security.getPermitAllUrls().add("/oauth/check_token"); //token检测默认使用的basic认证，因此就不需要用户名/密码身份验证
	}
	
}
