package com.penglecode.xmodule.common.security.oauth2;

import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.util.Assert;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

public class CustomClientCredentialsAccessTokenProvider extends ClientCredentialsAccessTokenProvider {

	private final RestOperations restTemplate;

	public CustomClientCredentialsAccessTokenProvider(RestOperations restTemplate) {
		super();
		Assert.notNull(restTemplate, "Parameter 'restTemplate' must be required!");
		this.restTemplate = restTemplate;
		this.setMessageConverters(new RestTemplate().getMessageConverters());
	}

	public RestOperations getRestTemplate() {
		return restTemplate;
	}

}
