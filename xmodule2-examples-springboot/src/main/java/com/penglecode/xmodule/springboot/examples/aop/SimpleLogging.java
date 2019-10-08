package com.penglecode.xmodule.springboot.examples.aop;

import java.lang.annotation.*;

/**
 * 简单日志记录注解
 * 
 * @author 	pengpeng
 * @date   		2017年6月1日 下午6:34:14
 * @version 	1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SimpleLogging {

}
