package com.penglecode.xmodule.common.boot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

import com.penglecode.xmodule.common.consts.ApplicationConstants;

/**
 * 默认的SpringBoot应用配置
 * 
 * @author 	pengpeng
 * @date	2019年8月28日 下午2:27:49
 */
@Configuration
public class DefaultSpringAppConfiguration extends AbstractSpringConfiguration {

	@Bean
	@ConditionalOnMissingBean(name="defaultConversionService")
	public ConversionService defaultConversionService() {
		return ApplicationConstants.DEFAULT_CONVERSION_SERVICE;
	}
	
}
