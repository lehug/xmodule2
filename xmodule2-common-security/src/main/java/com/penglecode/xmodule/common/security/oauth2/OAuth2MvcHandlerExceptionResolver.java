package com.penglecode.xmodule.common.security.oauth2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import com.penglecode.xmodule.common.support.ExceptionDescriptor;
import com.penglecode.xmodule.common.support.ExceptionDescriptorResolver;
import com.penglecode.xmodule.common.util.StringUtils;
import com.penglecode.xmodule.common.web.springmvc.handler.DefaultMvcHandlerExceptionResolver;

/**
 * OAuth2认证授权服务器的标准MVC异常处理器
 * 
 * @author 	pengpeng
 * @date	2019年2月18日 下午12:32:33
 */
public class OAuth2MvcHandlerExceptionResolver extends DefaultMvcHandlerExceptionResolver {

	private OAuth2ErrorMessageSource oauth2ErrorMessageSource = OAuth2ErrorMessageSource.INSTANCE;
	
	@Override
	protected ExceptionDescriptor resolveExceptionDescriptor(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {
		String message = null;
		int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
		if(ex instanceof OAuth2Exception) {
			String exMessage = null;
			OAuth2Exception oauth2Exception = (OAuth2Exception) ex;
			if(oauth2Exception.getHttpErrorCode() == HttpStatus.INTERNAL_SERVER_ERROR.value() && oauth2Exception.getCause() != null) { //500，服务器内部错误
				exMessage = ExceptionDescriptorResolver.resolveException(oauth2Exception.getCause()).getMessage();
				message = oauth2ErrorMessageSource.getErrorMessage(oauth2Exception.getOAuth2ErrorCode(), new Object[] {exMessage});
			} else {
				exMessage = oauth2Exception.getMessage();
				message = oauth2ErrorMessageSource.getErrorMessage(oauth2Exception.getOAuth2ErrorCode(), new Object[] {});
			}
			message = StringUtils.defaultIfEmpty(message, exMessage);
			code = oauth2Exception.getHttpErrorCode();
			return new ExceptionDescriptor(ex, code, message);
		}
		return super.resolveExceptionDescriptor(request, response, handler, ex);
	}

	public OAuth2ErrorMessageSource getOauth2ErrorMessageSource() {
		return oauth2ErrorMessageSource;
	}

	public void setOauth2ErrorMessageSource(OAuth2ErrorMessageSource oauth2ErrorMessageSource) {
		this.oauth2ErrorMessageSource = oauth2ErrorMessageSource;
	}
	
}
