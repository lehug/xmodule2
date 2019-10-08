package com.penglecode.xmodule.springboot.examples.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

public class SimpleLoggingAdvice extends SimpleLoggingSupport implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		StringBuilder sb = createLoggerBuilder(BeforeAdvice.class, method, args);
		System.out.println(sb.toString());
	}
	
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		StringBuilder sb = createLoggerBuilder(AfterAdvice.class, method, args);
		if(!Void.TYPE.equals(method.getReturnType())) {
			sb.append(" : " + returnValue);
		}
		System.out.println(sb.toString());
	}
	
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
		StringBuilder sb = createLoggerBuilder(ThrowsAdvice.class, method, args);
		if(!Void.TYPE.equals(method.getReturnType())) {
			sb.append(" : " + ex.getMessage());
		}
		System.out.println(sb.toString());
	}
	
}
