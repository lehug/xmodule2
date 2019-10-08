package com.penglecode.xmodule.common.security.oauth2;

import static com.penglecode.xmodule.common.consts.Constant.defaultOf;

import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import com.penglecode.xmodule.common.consts.GlobalApplicationNames;

/**
 * OAuth2常量
 * 
 * @author 	pengpeng
 * @date	2019年2月19日 下午12:38:24
 */
public class OAuth2ApplicationConstants {

	/**
	 * 当时用RemoteTokenServices进行token验证时,需要标记一下
	 */
	public static final String OAUTH2_TOKEN_SERVICE_REQUEST_SOURCE = defaultOf("TokenServiceRequestSource");
	
	/**
	 * ResourceServerTokenServices.loadAuthentication()返回结果在当前请求上下文Attributes中的缓存key
	 */
	public static final String RESULT_CACHE_KEY_LOAD_AUTHENTICATION = ResourceServerTokenServices.class.getName() + ".loadAuthentication";
	
	/**
	 * ResourceServerTokenServices.readAccessToken()返回结果在当前请求上下文Attributes中的缓存key
	 */
	public static final String RESULT_CACHE_KEY_READ_ACCESS_TOKEN = ResourceServerTokenServices.class.getName() + ".readAccessToken";
	
	/**
	 * 获取token服务的URL
	 */
	public static final String OAUTH2_TOKEN_SERVICE_URL = defaultOf("http://" + GlobalApplicationNames.AUTH_APPLICATION_NAME + "/oauth/token");
	
}
