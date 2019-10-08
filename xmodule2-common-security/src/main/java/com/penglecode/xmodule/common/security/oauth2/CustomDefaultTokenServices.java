package com.penglecode.xmodule.common.security.oauth2;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.penglecode.xmodule.common.util.SpringWebMvcUtils;

/**
 * 默认的自定义的TokenServices
 * 
 * 实现以下功能：
 * 	  基于当前请求上下文的可缓存TokenServices，
 *    即将首次获取的token信息缓存到请求上下文的Attribute中去,避免在一个请求的上下文中多次读取Redis
 * 
 * @author 	pengpeng
 * @date	2019年6月15日 下午4:22:07
 */
public class CustomDefaultTokenServices extends DefaultTokenServices implements ResourceServerTokenServices, MyConsumerTokenServices {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomDefaultTokenServices.class);
	
	private TokenStore tokenStore;
	
	@Override
	public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
		clearRequestScopeCache();
		return super.createAccessToken(authentication);
	}

	@Override
	public OAuth2AccessToken refreshAccessToken(String refreshTokenValue, TokenRequest tokenRequest)
			throws AuthenticationException {
		clearRequestScopeCache();
		return super.refreshAccessToken(refreshTokenValue, tokenRequest);
	}

	@Override
	public OAuth2AccessToken readAccessToken(String accessToken) {
		String cacheAttrKey = OAuth2ApplicationConstants.RESULT_CACHE_KEY_READ_ACCESS_TOKEN;
		HttpServletRequest request = SpringWebMvcUtils.getCurrentHttpServletRequest();
		if(request != null) {
			OAuth2AccessToken cachedAccessToken = (OAuth2AccessToken) request.getAttribute(cacheAttrKey);
			if(cachedAccessToken != null) {
				LOGGER.debug(">>> Read AccessToken cache ({}) from current request context!", cachedAccessToken);
				return cachedAccessToken;
			}
		}
		OAuth2AccessToken newAccessToken = super.readAccessToken(accessToken);
		if(request != null) {
			request.setAttribute(cacheAttrKey, newAccessToken);
		}
		LOGGER.debug(">>> Read AccessToken newly ({}) from remote TokenStore!", newAccessToken);
		return newAccessToken;
	}

	@Override
	public OAuth2Authentication loadAuthentication(String accessTokenValue)
			throws AuthenticationException, InvalidTokenException {
		String cacheAttrKey = OAuth2ApplicationConstants.RESULT_CACHE_KEY_LOAD_AUTHENTICATION;
		HttpServletRequest request = SpringWebMvcUtils.getCurrentHttpServletRequest();
		if(request != null) {
			OAuth2Authentication cachedAuthentication = (OAuth2Authentication) request.getAttribute(cacheAttrKey);
			if(cachedAuthentication != null) {
				LOGGER.debug(">>> Load Authentication cache ({}) from current request context!", cachedAuthentication);
				return cachedAuthentication;
			}
		}
		OAuth2Authentication newAuthentication = super.loadAuthentication(accessTokenValue);
		if(request != null) {
			request.setAttribute(cacheAttrKey, newAuthentication);
		}
		LOGGER.debug(">>> Load Authentication newly ({}) from remote TokenStore!", newAuthentication);
		return newAuthentication;
	}

	@Override
	public Collection<OAuth2AccessToken> getAccessTokensByClientIdAndUserName(String clientId, String userName) {
		return tokenStore.findTokensByClientIdAndUserName(clientId, userName);
	}

	@Override
	public Collection<OAuth2AccessToken> getAccessTokensByClientId(String clientId) {
		return tokenStore.findTokensByClientId(clientId);
	}

	@Override
	public void setTokenStore(TokenStore tokenStore) {
		super.setTokenStore(tokenStore);
		this.tokenStore = tokenStore;
	}

	protected TokenStore getTokenStore() {
		return tokenStore;
	}
	
	protected void clearRequestScopeCache() {
		//清除缓存
		HttpServletRequest request = SpringWebMvcUtils.getCurrentHttpServletRequest();
		request.removeAttribute(OAuth2ApplicationConstants.RESULT_CACHE_KEY_READ_ACCESS_TOKEN);
		request.removeAttribute(OAuth2ApplicationConstants.RESULT_CACHE_KEY_LOAD_AUTHENTICATION);
	}
	
}
