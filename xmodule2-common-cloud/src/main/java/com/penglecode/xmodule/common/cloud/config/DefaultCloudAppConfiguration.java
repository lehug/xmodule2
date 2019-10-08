package com.penglecode.xmodule.common.cloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.penglecode.xmodule.common.boot.config.AbstractSpringConfiguration;
import com.penglecode.xmodule.common.cloud.support.CloudSecurityConfigProperties;
import com.penglecode.xmodule.common.consts.GlobalConstants;
import com.penglecode.xmodule.common.support.GlobalAppConfig;
import com.penglecode.xmodule.common.support.MvvmAppConfig;

/**
 * 默认SpringCloud应用方面的配置
 * 
 * @author 	pengpeng
 * @date	2019年6月13日 下午6:54:59
 */
@Configuration
public class DefaultCloudAppConfiguration extends AbstractSpringConfiguration {

	/**
	 * 全局应用配置
	 * @return
	 */
	@Bean
	@RefreshScope
	@ConfigurationProperties(prefix="appone.global")
	public GlobalAppConfig globalAppConfig() {
		return GlobalConstants.GLOBAL_APP_CONFIG;
	}
	
	/**
	 * 前后端分离开发模式的应用配置(包括应用的name, url, contextpath等)
	 * @return
	 */
	@Bean
	@ConfigurationProperties(prefix="appone.mvvm")
	public MvvmAppConfig mvvmAppConfig() {
		return GlobalConstants.MVVM_APP_CONFIG;
	}
	
	/**
	 * SpringCloud微服务之间相互调用的安全配置
	 */
	@Bean
	@ConfigurationProperties(prefix="spring.cloud.security.config")
	public CloudSecurityConfigProperties cloudSecurityConfigProperties() {
		return new CloudSecurityConfigProperties();
	}
	
}
