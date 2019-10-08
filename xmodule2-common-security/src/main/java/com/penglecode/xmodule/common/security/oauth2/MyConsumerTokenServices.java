package com.penglecode.xmodule.common.security.oauth2;

import java.util.Collection;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;

/**
 * 自定义的ConsumerTokenServices
 * 
 * @author 	pengpeng
 * @date	2019年7月26日 下午5:05:12
 */
public interface MyConsumerTokenServices extends ConsumerTokenServices {

	/**
	 * 根据客户端ID和userName获取符合条件的所有OAuth2AccessToken
	 * @param clientId
	 * @param userName
	 * @return
	 */
	Collection<OAuth2AccessToken> getAccessTokensByClientIdAndUserName(String clientId, String userName);
	
	/**
	 * 根据客户端ID获取该客户端下的所有OAuth2AccessToken
	 * @param clientId
	 * @return
	 */
	Collection<OAuth2AccessToken> getAccessTokensByClientId(String clientId);
	
}
