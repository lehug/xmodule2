package com.penglecode.xmodule.common.security.config;

import static com.penglecode.xmodule.common.redis.LettuceConnectionFactoryUtils.createLettuceClientConfiguration;
import static com.penglecode.xmodule.common.redis.LettuceConnectionFactoryUtils.createLettuceConnectionFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.http.converter.jaxb.JaxbOAuth2ExceptionMessageConverter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import com.penglecode.xmodule.common.boot.config.AbstractSpringConfiguration;
import com.penglecode.xmodule.common.security.consts.SecurityApplicationConstants;
import com.penglecode.xmodule.common.security.oauth2.CustomDefaultTokenServices;
import com.penglecode.xmodule.common.security.oauth2.CustomOAuth2ExceptionRenderer;
import com.penglecode.xmodule.common.security.oauth2.CustomTokenEnhancer;
import com.penglecode.xmodule.common.security.oauth2.Jackson2SerializationStrategy;
import com.penglecode.xmodule.common.security.oauth2.OAuth2ClientConfigProperties;
import com.penglecode.xmodule.common.security.oauth2.OAuth2ServerConfigProperties;
import com.penglecode.xmodule.common.util.BeanUtils;

import io.lettuce.core.resource.ClientResources;

/**
 * 默认的OAuth2认证授权的authrization-server和resource-server的公共配置
 * 
 * @author 	pengpeng
 * @date	2019年6月13日 上午9:49:03
 */
@Configuration
public class DefaultOAuth2Configuration extends AbstractSpringConfiguration {

	@Bean
	@ConfigurationProperties(prefix="spring.security.oauth2.client")
	public OAuth2ClientConfigProperties oauth2ClientConfig() {
		return new OAuth2ClientConfigProperties();
	}
	
	@Bean
	@ConfigurationProperties(prefix="spring.security.oauth2.server")
	public OAuth2ServerConfigProperties oauth2ServerConfig() {
		return new OAuth2ServerConfigProperties();
	}
	
	@Bean
	@ConditionalOnMissingBean
	public JaxbOAuth2ExceptionMessageConverter jaxbOAuth2ExceptionMessageConverter() {
		return new JaxbOAuth2ExceptionMessageConverter();
	}
	
	@Bean
	@ConditionalOnMissingBean
	public PasswordEncoder passwordEncoder() {
		return SecurityApplicationConstants.DEFAULT_PASSWORD_ENCODER;
	}
	
	/**
	 * OAuth2认证授权异常渲染
	 * @return
	 */
	@Bean(name="oauth2ExceptionRenderer")
	public CustomOAuth2ExceptionRenderer oauth2ExceptionRenderer() {
		CustomOAuth2ExceptionRenderer oauth2ExceptionRenderer = new CustomOAuth2ExceptionRenderer();
		oauth2ExceptionRenderer.setResponseContentType(MediaType.APPLICATION_JSON_UTF8);
		return oauth2ExceptionRenderer;
	}
	
	/**
	 * 禁止访问处理
	 * @return
	 */
	@Bean(name="oauth2AccessDeniedHandler")
	public OAuth2AccessDeniedHandler oauth2AccessDeniedHandler() {
		OAuth2AccessDeniedHandler oauth2AccessDeniedHandler = new OAuth2AccessDeniedHandler();
		oauth2AccessDeniedHandler.setExceptionRenderer(oauth2ExceptionRenderer());
		return oauth2AccessDeniedHandler;
	}
	
	@Configuration
	@ConditionalOnProperty(prefix="spring.redis.security", name="host", matchIfMissing=false)
	public static class DefaultOAuth2TokenAccessConfiguration {
		
		@Bean(name="securityRedisProperties")
		@ConfigurationProperties(prefix="spring.redis.security")
		public RedisProperties securityRedisProperties(@Qualifier("commonRedisProperties")RedisProperties commonRedisProperties) {
			return BeanUtils.deepClone(commonRedisProperties);
		}
		
		@Bean(name="securityClientConfiguration")
		public LettuceClientConfiguration securityClientConfiguration(
				@Qualifier("securityRedisProperties")RedisProperties properties,
				@Qualifier("defaultClientResources")ClientResources clientResources
				) {
			return createLettuceClientConfiguration(properties, clientResources, properties.getLettuce().getPool());
		}
		
		@Bean(name="securityRedisConnectionFactory")
		public RedisConnectionFactory securityRedisConnectionFactory(
				@Qualifier("securityRedisProperties")RedisProperties properties,
				@Qualifier("securityClientConfiguration")LettuceClientConfiguration clientConfiguration) {
			return createLettuceConnectionFactory(properties, clientConfiguration);
		}
		
		/**
		 * 基于Redis的TokenStore
		 * @param redisConnectionFactory
		 * @return
		 */
		@Bean(name="redisTokenStore")
	    public TokenStore redisTokenStore(@Qualifier("securityRedisConnectionFactory")RedisConnectionFactory redisConnectionFactory) {
			RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
			tokenStore.setSerializationStrategy(new Jackson2SerializationStrategy());
			//tokenStore.setSerializationStrategy(new ProtostuffSerializationStrategy());
			return tokenStore;
	    }
		
		/**
		 * 自定义的TokenEnhancer
		 * @return
		 */
		@Bean
		public TokenEnhancer customTokenEnhancer() {
			return new CustomTokenEnhancer();
		}
		
		/**
		 * 默认的Token服务
		 * @return
		 */
		@Bean(name="tokenServices")
		public ResourceServerTokenServices tokenServices(@Qualifier("redisTokenStore") TokenStore tokenStore) {
			DefaultTokenServices tokenServices = new CustomDefaultTokenServices();
			tokenServices.setTokenStore(tokenStore);
			tokenServices.setSupportRefreshToken(true);
			tokenServices.setClientDetailsService(null);
			return tokenServices;
		}
		
	}
	
}
