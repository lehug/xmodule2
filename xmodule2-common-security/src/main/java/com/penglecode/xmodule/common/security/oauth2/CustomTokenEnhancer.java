package com.penglecode.xmodule.common.security.oauth2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.penglecode.xmodule.common.security.support.UpmsLoginUser;

/**
 * 自定义的TokenEnhancer
 * 
 * 1、重写了token的值
 * 2、增加了关联的登录用户ID和用户名
 * 
 * @author 	pengpeng
 * @date	2019年7月27日 上午11:27:13
 */
public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		if (accessToken instanceof DefaultOAuth2AccessToken) {
            DefaultOAuth2AccessToken token = ((DefaultOAuth2AccessToken) accessToken);
            token.setValue(token.getValue().replace("-", ""));
            OAuth2RefreshToken refreshToken = token.getRefreshToken();
            if (refreshToken instanceof DefaultOAuth2RefreshToken) {
            	String refreshTokenValue = refreshToken.getValue().replace("-", "");
            	if(refreshToken instanceof DefaultExpiringOAuth2RefreshToken) {
            		OAuth2RefreshToken newRefreshToken = new DefaultExpiringOAuth2RefreshToken(refreshTokenValue, ((DefaultExpiringOAuth2RefreshToken) refreshToken).getExpiration());
            		token.setRefreshToken(newRefreshToken);
            	} else {
            		token.setRefreshToken(new DefaultOAuth2RefreshToken(refreshTokenValue));
            	}
            }
            Map<String, Object> additionalInformation = new HashMap<String, Object>();
            if(!authentication.isClientOnly()) {
            	UpmsLoginUser loginUser = (UpmsLoginUser) authentication.getPrincipal();
            	additionalInformation.put("userId", loginUser.getUserId());
            	additionalInformation.put("userName", loginUser.getUserName());
                token.setAdditionalInformation(additionalInformation);
            }
            return token;
        } 
        return accessToken;
	}

}
