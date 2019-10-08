package com.penglecode.xmodule.springboot.examples.aop.autoproxy.simple;

import java.util.Map;

import org.springframework.aop.SpringProxy;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

import com.penglecode.xmodule.common.consts.ApplicationConstants;

public class AutoProxyExample implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		ApplicationContext applicationContext = ApplicationConstants.APPLICATION_CONTEXT;
		
		System.out.println("<-------------------------------AccountServiceProxyExample----------------------------->");
		Map<String,AccountService> accountServiceBeans = applicationContext.getBeansOfType(AccountService.class);
		
		accountServiceBeans.forEach((name, bean) -> {
			System.out.println(name + " = " + bean + " : " + bean.getClass());
		});
		
		AccountService accountService = (AccountService) applicationContext.getBean("accountService");
		
		System.out.println(">>> accountService instanceof Proxy: " + ((Object)accountService instanceof java.lang.reflect.Proxy)); //false
		System.out.println(">>> accountService instanceof Proxy: " + ((Object)accountService instanceof org.springframework.cglib.proxy.Proxy)); //false
		System.out.println(">>> accountService instanceof SpringProxy: " + ((Object)accountService instanceof SpringProxy)); //true
		System.out.println(">>> AopUtils.isJdkDynamicProxy(accountService): " + AopUtils.isJdkDynamicProxy(accountService)); //false
		System.out.println(">>> AopUtils.isCglibProxy(accountService): " + AopUtils.isCglibProxy(accountService)); //true
		System.out.println(">>> AopUtils.getTargetClass(accountService): " + AopUtils.getTargetClass(accountService)); //
		AccountService target = (AccountService) AopProxyUtils.getSingletonTarget(accountService);
		if(target != null) {
			System.out.println(">>> AopProxyUtils.getSingletonTarget(accountService): " + target); //
			System.out.println(">>> target.getClass(): " + target.getClass());
			target.getAccountById(1L);
		}
		
		System.out.println("<-------------------------------accountService.createAccount----------------------------->");
		Account account = new Account();
		account.setAccountId(System.currentTimeMillis());
		account.setAccountNo(account.getAccountId().toString());
		account.setAccountBalance(1200.0);
		accountService.createAccount(account);
		
		System.out.println("<-------------------------------accountService.updateAccount----------------------------->");
		account.setAccountBalance(1500.0);
		accountService.updateAccount(account);
		
		System.out.println("<-------------------------------accountService.getAccountById----------------------------->");
		accountService.getAccountById(account.getAccountId());
	}

}
