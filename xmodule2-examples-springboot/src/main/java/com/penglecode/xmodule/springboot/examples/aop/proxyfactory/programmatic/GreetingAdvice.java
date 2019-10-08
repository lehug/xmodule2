package com.penglecode.xmodule.springboot.examples.aop.proxyfactory.programmatic;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.penglecode.xmodule.common.util.ArrayUtils;

/**
 * Greeting通知
 * 
 * 对GreetingService的环绕拦截器
 * 
 * @author 	pengpeng
 * @date	2019年9月26日 下午4:25:15
 */
public class GreetingAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method method = invocation.getMethod();
		String methodName = method.getName();
		Object[] methodArgs = invocation.getArguments();
		StringBuilder sb = null;
		sb = new StringBuilder();
		sb.append(String.format(">>> invoke %s", methodName));
		sb.append("(");
		if(!ArrayUtils.isEmpty(methodArgs)) {
			for(int i = 0, len = methodArgs.length; i < len; i++) {
				sb.append(methodArgs[i]);
				if(i != len - 1) {
					sb.append(", ");
				}
			}
		}
		sb.append(")");
		System.out.println(sb.toString());
		
		Object value = invocation.proceed();
		
		sb = new StringBuilder();
		sb.append(String.format("<<< invoke %s", methodName));
		sb.append("(");
		if(!ArrayUtils.isEmpty(methodArgs)) {
			for(int i = 0, len = methodArgs.length; i < len; i++) {
				sb.append(methodArgs[i]);
				if(i != len - 1) {
					sb.append(", ");
				}
			}
		}
		sb.append(")");
		if(!Void.TYPE.equals(method.getReturnType())) {
			sb.append(" : " + value);
		}
		System.out.println(sb.toString());
		return value;
	}

}
