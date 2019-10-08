package com.penglecode.xmodule.common.security.oauth2;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.util.AntPathMatcher;

import com.penglecode.xmodule.common.util.CollectionUtils;

/**
 * 自定义的OAuth2RestTemplate，可以排除一些不需要OAuth2认证授权的URL
 * 
 * @author 	pengpeng
 * @date	2019年6月24日 下午12:40:48
 */
public class CustomOAuth2RestTemplate extends OAuth2RestTemplate implements InitializingBean {

	private static final List<String> MUST_EXCLUDE_AUTHORIZATION_TOKEN_URLS = Arrays.asList("/oauth/token");
	
	private AntPathMatcher requestPathMatcher = new AntPathMatcher();
	
	private Set<String> excludeAuthorizationTokenUrls;
	
	public CustomOAuth2RestTemplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
		super(resource, context);
		setAccessTokenProvider(new AccessTokenProviderChain(Arrays.<AccessTokenProvider> asList(
				new AuthorizationCodeAccessTokenProvider(), new ImplicitAccessTokenProvider(),
				new ResourceOwnerPasswordAccessTokenProvider(), new CustomClientCredentialsAccessTokenProvider(this))));
	}

	public CustomOAuth2RestTemplate(OAuth2ProtectedResourceDetails resource) {
		super(resource);
		setAccessTokenProvider(new AccessTokenProviderChain(Arrays.<AccessTokenProvider> asList(
				new AuthorizationCodeAccessTokenProvider(), new ImplicitAccessTokenProvider(),
				new ResourceOwnerPasswordAccessTokenProvider(), new CustomClientCredentialsAccessTokenProvider(this))));
	}
	
	@Override
	protected ClientHttpRequest createRequest(URI uri, HttpMethod method) throws IOException {
		String path = uri.getPath();
		boolean applyAuthorization = true;
		if(!CollectionUtils.isEmpty(excludeAuthorizationTokenUrls)) {
			for(String excludePattern : excludeAuthorizationTokenUrls) {
				if(requestPathMatcher.match(excludePattern, path)) {
					applyAuthorization = false;
					break;
				}
			}
		}
		if(applyAuthorization) {
			return super.createRequest(uri, method);
		} else {
			return createRequest0(uri, method);
		}
	}
	
	protected ClientHttpRequest createRequest0(URI url, HttpMethod method) throws IOException {
		ClientHttpRequest request = getRequestFactory().createRequest(url, method);
		if (logger.isDebugEnabled()) {
			logger.debug("HTTP " + method.name() + " " + url);
		}
		return request;
	}
	
	public AntPathMatcher getRequestPathMatcher() {
		return requestPathMatcher;
	}

	public void setRequestPathMatcher(AntPathMatcher requestPathMatcher) {
		this.requestPathMatcher = requestPathMatcher;
	}

	public Set<String> getExcludeAuthorizationTokenUrls() {
		return excludeAuthorizationTokenUrls;
	}

	public void setExcludeAuthorizationTokenUrls(Set<String> excludeAuthorizationTokenUrls) {
		this.excludeAuthorizationTokenUrls = excludeAuthorizationTokenUrls;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(excludeAuthorizationTokenUrls == null) {
			excludeAuthorizationTokenUrls = new HashSet<String>();
		}
		excludeAuthorizationTokenUrls.addAll(MUST_EXCLUDE_AUTHORIZATION_TOKEN_URLS);
	}

}
