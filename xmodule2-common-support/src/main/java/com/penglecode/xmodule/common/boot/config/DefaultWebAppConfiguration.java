package com.penglecode.xmodule.common.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.request.RequestContextListener;

import com.penglecode.xmodule.common.web.support.DefaultHttpAccessExceptionHandler;
import com.penglecode.xmodule.common.web.support.HttpAccessExceptionHandler;
import com.penglecode.xmodule.common.web.support.SmartXUploadFileTransfer;
import com.penglecode.xmodule.common.web.support.XUploadFileHelper;

/**
 * 默认的WEB应用配置
 * 
 * @author 	pengpeng
 * @date	2018年2月3日 下午5:53:07
 */
@Configuration
@EnableScheduling
public class DefaultWebAppConfiguration extends AbstractSpringConfiguration {

	/**
	 * 小文件上传助手
	 * @return
	 */
	@Bean(name="defaultXUploadFileHelper")
	public XUploadFileHelper defaultXUploadFileHelper() {
		return new SmartXUploadFileTransfer();
	}
	
	/**
	 * 配置默认的Http访问异常处理器
	 * @return
	 */
	@Bean(name="defaultHttpAccessExceptionHandler")
	public HttpAccessExceptionHandler defaultHttpAccessExceptionHandler() {
		return new DefaultHttpAccessExceptionHandler();
	}
	
	/**
	 * 配置RequestContextListener
	 * @return
	 */
	@Bean 
	public RequestContextListener requestContextListener(){
	    return new RequestContextListener();
	}
	
}
