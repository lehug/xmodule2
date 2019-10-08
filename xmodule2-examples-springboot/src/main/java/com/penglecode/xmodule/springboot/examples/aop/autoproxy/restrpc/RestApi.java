package com.penglecode.xmodule.springboot.examples.aop.autoproxy.restrpc;

import java.lang.annotation.*;

/**
 * SpringMVC rest client
 * 
 * @author 	pengpeng
 * @date   		2017年6月1日 下午6:34:14
 * @version 	1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RestApi {

	String clientName();
	
	String clientUrl();
	
	String clientDesc() default "";
	
}
