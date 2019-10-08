package com.penglecode.xmodule.springboot.examples.aop.proxyfactory.configurable;

import java.util.Map;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

import com.penglecode.xmodule.common.consts.ApplicationConstants;

public class ProxyFactoryBeanExample implements InitializingBean {
	
	@Override
	public void afterPropertiesSet() throws Exception {
		ApplicationContext applicationContext = ApplicationConstants.APPLICATION_CONTEXT;
				
		System.out.println("<-------------------------------MessageServiceProxyExample----------------------------->");
		Map<String,MessageService> messageServiceBeans = applicationContext.getBeansOfType(MessageService.class);
		
		messageServiceBeans.forEach((name, bean) -> {
			System.out.println(bean + " : " + bean.getClass());
		});
		MessageService messageServiceProxy1 = (MessageService) applicationContext.getBean("messageServiceProxy1");
		
		System.out.println("<-------------------------------inspect----------------------------->");
		System.out.println(">>> AopUtils.getTargetClass(messageServiceProxy1): " + AopUtils.getTargetClass(messageServiceProxy1)); //
		MessageService target = (MessageService) AopProxyUtils.getSingletonTarget(messageServiceProxy1);
		if(target != null) {
			System.out.println(">>> AopProxyUtils.getSingletonTarget(messageServiceProxy1): " + target); //
			System.out.println(">>> target.getClass(): " + target.getClass());
			target.sendSms("123456", "test");
		}
		
		System.out.println("<-------------------------------messageServiceProxy1----------------------------->");
		System.out.println(messageServiceProxy1 + " : " + messageServiceProxy1.getClass());
		System.out.println("<-------------------------------messageServiceProxy1.sendSms----------------------------->");
		messageServiceProxy1.sendSms("13912345678", "您的验证码：762381");
		System.out.println("<-------------------------------messageServiceProxy1.sendEmail----------------------------->");
		messageServiceProxy1.sendEmail("87180232560@qq.com", "注册验证码", "您的验证码：477192");
		
	}
	
}