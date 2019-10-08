package com.penglecode.xmodule.common.consts;

import org.springframework.core.env.Environment;

import com.penglecode.xmodule.common.consts.Constant;
import com.penglecode.xmodule.common.consts.ConstantPool;

public class SpringEnvConstantPool<T> implements ConstantPool<T> {

	/**
	 * Spring的Environment
	 */
	private static volatile Environment environment;
	
	public static void setEnvironment(Environment environment) {
		SpringEnvConstantPool.environment = environment;
		Constant.setConstantPool(new SpringEnvConstantPool<Object>());
	}
	
	public static Environment getEnvironment() {
		return environment;
	}
	
	@Override
	public T valueOf(String name, Class<T> constType, T defaultValue) {
		return getEnvironment().getProperty(name, constType, defaultValue);
	}

}
