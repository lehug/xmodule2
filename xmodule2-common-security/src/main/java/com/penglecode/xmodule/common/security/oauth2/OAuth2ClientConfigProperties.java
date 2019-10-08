package com.penglecode.xmodule.common.security.oauth2;

import org.springframework.beans.factory.InitializingBean;

import com.penglecode.xmodule.platform.upms.consts.UpmsConstants;

/**
 * OAuth2资源服务配置
 * 
 * @author 	pengpeng
 * @date	2019年2月19日 下午2:43:18
 */
public class OAuth2ClientConfigProperties implements InitializingBean {

    /** 客户端访问的资源ID */
    private String resourceId;
    
    /** 用户客户端(用户调用服务)配置 */
    private OAuth2Client userClient;
    
    /** 应用客户端(应用之间互调)配置 */
    private OAuth2Client appClient;
    
    /** 安全配置 */
    private OAuth2SecurityConfig security;
    
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public OAuth2Client getUserClient() {
		return userClient;
	}

	public void setUserClient(OAuth2Client userClient) {
		this.userClient = userClient;
	}

	public OAuth2Client getAppClient() {
		return appClient;
	}

	public void setAppClient(OAuth2Client appClient) {
		this.appClient = appClient;
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
		security.getPermitAllUrls().add("/api/server/info");
		security.getPermitAllUrls().add(UpmsConstants.DEFAULT_UPMS_USER_LOGIN_URL);
	}

}
