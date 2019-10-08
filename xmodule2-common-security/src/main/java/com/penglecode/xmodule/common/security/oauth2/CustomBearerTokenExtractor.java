package com.penglecode.xmodule.common.security.oauth2;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.util.AntPathMatcher;

import com.penglecode.xmodule.common.util.CollectionUtils;

/**
 * 自定义的BearerTokenExtractor
 * 
 * 用于解决某个URL不需要携带 'Authorization'头，但是却携带了invalid的Authorization值，导致出现InvalidTokenException: Invalid access token: xxxx
 * 
 * @author 	pengpeng
 * @date	2019年7月8日 上午11:33:17
 */
public class CustomBearerTokenExtractor extends BearerTokenExtractor {

	private final Set<String> excludeAuthorizationTokenUrls;
	
	private AntPathMatcher requestUriMatcher = new AntPathMatcher();

	public CustomBearerTokenExtractor(Set<String> excludeAuthorizationTokenUrls) {
		super();
		this.excludeAuthorizationTokenUrls = excludeAuthorizationTokenUrls;
	}

	@Override
	public Authentication extract(HttpServletRequest request) {
		if(isExcludeAuthorizationTokenUrl(request)) {
			return null;
		}
		return super.extract(request);
	}
	
	protected boolean isExcludeAuthorizationTokenUrl(HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		if(!CollectionUtils.isEmpty(excludeAuthorizationTokenUrls)) {
			for(String excludePattern : excludeAuthorizationTokenUrls) {
				if(requestUriMatcher.match(excludePattern, requestUri)) {
					return true;
				}
			}
		}
		return false;
	}

	public AntPathMatcher getRequestUriMatcher() {
		return requestUriMatcher;
	}

	public void setRequestUriMatcher(AntPathMatcher requestUriMatcher) {
		this.requestUriMatcher = requestUriMatcher;
	}

	public Set<String> getExcludeAuthorizationTokenUrls() {
		return excludeAuthorizationTokenUrls;
	}
	
}
