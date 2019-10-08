package com.penglecode.xmodule.common.security.oauth2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.util.Assert;

import com.penglecode.xmodule.common.security.support.UpmsLoginUser;
import com.penglecode.xmodule.common.support.ValidationAssert;
import com.penglecode.xmodule.common.util.SpringWebMvcUtils;
import com.penglecode.xmodule.common.web.support.HttpAPIResourceSupport;

/**
 * 基于OAuth2认证的接口API辅助类
 * 
 * @author 	pengpeng
 * @date	2019年2月25日 下午3:49:07
 */
public abstract class OAuth2HttpApiResourceSupport extends HttpAPIResourceSupport {

	@Autowired
	private ResourceServerTokenServices tokenServices;
	
	/**
	 * 从当前请求头中获取名为'Authorization'的accessToken值
	 * @return
	 */
	protected String getAccessTokenFromCurrentRequest() {
		HttpServletRequest request = SpringWebMvcUtils.getCurrentHttpServletRequest();
		return OAuth2Utils.extractHeaderToken(request);
	}
	
	/**
	 * 获取当前请求所带token指示的已认证token信息
	 * @return
	 */
	protected OAuth2Authentication getOAuth2Authentication() {
		String token = getAccessTokenFromCurrentRequest();
		Assert.hasText(token, "No OAuth2 'Authorization' token found in request header!");
		return getTokenServices().loadAuthentication(token);
	}
	
	/**
	 * 当授权模式为authorization_code/password模式时，获取当前请求所带token指示的已认证用户信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getAuthenticatedUser() {
		OAuth2Authentication authentication = getOAuth2Authentication();
		ValidationAssert.isTrue(!authentication.isClientOnly(), "No userAuthentication available for OAuth2 client_credentials mode!");
		return authentication.isClientOnly() ? null : (T) authentication.getPrincipal();
	}
	
	/**
	 * 当授权模式为authorization_code/password模式时，获取当前请求所带token指示的已认证用户ID
	 * @return
	 */
	protected Long getAuthenticatedUserId() {
		UpmsLoginUser loginUser = getAuthenticatedUser();
		return loginUser.getUserId();
	}
	
	/**
	 * 获取登录用户信息的Map形式
	 * @param loginUser
	 * @return
	 */
	protected Map<String,Object> getLoginUserInfoMap(UpmsLoginUser loginUser) {
		Map<String,Object> user = loginUser.asMap();
		user.put("qq", null);
		user.put("messages", new ArrayList<Object>()); //TODO
		user.put("messageBadges", 0); //TODO
		user.put("notices", new ArrayList<Object>()); //TODO
		user.put("noticeBadges", 0); //TODO
		
		Collection<? extends GrantedAuthority> userAuthorities = loginUser.getAuthorities();
		List<String> userRoles = userAuthorities.stream().map(GrantedAuthority::getAuthority).distinct().collect(Collectors.toList());
		user.put("roles", userRoles);
		user.put("roleBadges", userRoles.size());
		user.put("loginTimes", loginUser.getLoginTimes());
		return user;
	}
	
	/**
	 * 当授权模式为client_credentials模式时，获取当前请求所带token指示的已认客户端ID
	 * @return
	 */
	protected String getAuthenticatedClient() {
		OAuth2Authentication authentication = getOAuth2Authentication();
		return authentication.isClientOnly() ? (String) authentication.getPrincipal() : null;
	}

	protected ResourceServerTokenServices getTokenServices() {
		return tokenServices;
	}

	protected void setTokenServices(ResourceServerTokenServices tokenServices) {
		this.tokenServices = tokenServices;
	}
	
}
