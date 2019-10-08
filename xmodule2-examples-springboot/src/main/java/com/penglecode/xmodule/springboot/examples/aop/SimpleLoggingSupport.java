package com.penglecode.xmodule.springboot.examples.aop;

import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import com.penglecode.xmodule.common.util.ArrayUtils;

public class SimpleLoggingSupport {

	private final ParameterNameDiscoverer defaultarameterNameDiscoverer = new DefaultParameterNameDiscoverer();
	
	protected StringBuilder createLoggerBuilder(Class<? extends Advice> adviceType, Method method, Object[] args) {
		String methodName = method.getName();
		String[] methodArgNames = defaultarameterNameDiscoverer.getParameterNames(method);
		Object[] methodArgValues = args;
		
		StringBuilder sb = new StringBuilder();
		if(BeforeAdvice.class.equals(adviceType)) {
			sb.append(String.format(">>> Prepare to call %s", methodName));
		} else if (ThrowsAdvice.class.equals(adviceType)) {
			sb.append(String.format("<<< Failed to call %s", methodName));
		} else {
			sb.append(String.format("<<< Succeed to call %s", methodName));
		}
		
		sb.append("(");
		if(!ArrayUtils.isEmpty(methodArgNames)) {
			for(int i = 0, len = methodArgNames.length; i < len; i++) {
				sb.append(methodArgNames[i] + " = " + methodArgValues[i]);
				if(i != len - 1) {
					sb.append(", ");
				}
			}
		}
		sb.append(")");
		return sb;
	}
	
	protected ParameterNameDiscoverer getDefaultarameterNameDiscoverer() {
		return defaultarameterNameDiscoverer;
	}
	
}
