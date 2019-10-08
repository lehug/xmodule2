package com.penglecode.xmodule.springboot.examples.aop.proxyfactory.programmatic;

import org.springframework.aop.SpringProxy;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AbstractRegexpMethodPointcut;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

/**
 * {@link #ProxyFactory}使用示例
 * 
 * @author 	pengpeng
 * @date	2019年9月26日 下午3:44:29
 */
public class ProxyFactoryExample {

	/**
	 * 该示例中,通知GreetingAdvice被应用到了目标GreetingService中的每个方法,包括Object的一些列方法(toString,equals等等)
	 */
	public static void testGreetingService1Proxy1() {
		ProxyFactory proxyFactory = new ProxyFactory();
		Object target = new GreetingService1();
		proxyFactory.setTarget(target);
		System.out.println(">>> target: " + target);
		proxyFactory.addAdvice(new GreetingAdvice());
		
		GreetingService1 proxy = (GreetingService1) proxyFactory.getProxy();
		System.out.println(">>> proxy.equals(target): " + proxy.equals(target)); //false, 在未重写equals方法的情况下,比的是内存地址,所以为false
		System.out.println(">>> proxy.getClass(): " + proxy.getClass()); //xxx.GreetingService$$EnhancerBySpringCGLIB$$xxx
		System.out.println(">>> proxy instanceof Proxy: " + ((Object)proxy instanceof java.lang.reflect.Proxy)); //false
		System.out.println(">>> proxy instanceof Proxy: " + ((Object)proxy instanceof org.springframework.cglib.proxy.Proxy)); //false
		System.out.println(">>> proxy instanceof SpringProxy: " + ((Object)proxy instanceof SpringProxy)); //true
		System.out.println(">>> AopUtils.isJdkDynamicProxy(proxy): " + AopUtils.isJdkDynamicProxy(proxy)); //false
		System.out.println(">>> AopUtils.isCglibProxy(proxy): " + AopUtils.isCglibProxy(proxy)); //true
		System.out.println(proxy); //toString()方法被代理了
		System.out.println("---------------------sayHello()-------------------");
		proxy.sayHello();
		System.out.println("---------------------sayWord()-------------------");
		proxy.sayWord("Spring AOP");
		System.out.println("---------------------sayBye-------------------");
		proxy.sayBye();
	}
	
	/**
	 * 该示例中,通知GreetingAdvice通过Pointcut被应用到了目标GreetingService中的某些特定方法
	 */
	public static void testGreetingService1Proxy2() {
		ProxyFactory proxyFactory = new ProxyFactory();
		GreetingService1 target = new GreetingService1();
		proxyFactory.setTarget(target);
		System.out.println(">>> target: " + target);
		
		AbstractRegexpMethodPointcut regexMethodPointcut = new JdkRegexpMethodPointcut();
		regexMethodPointcut.setPattern(".*say.*");
		proxyFactory.addAdvisor(new DefaultPointcutAdvisor(regexMethodPointcut, new GreetingAdvice()));
		
		System.out.println("---------------------inspect-------------------");
		GreetingService1 proxy = (GreetingService1) proxyFactory.getProxy();
		System.out.println(">>> proxy.equals(target): " + proxy.equals(target)); //false, 在未重写equals方法的情况下,比的是内存地址,所以为false
		System.out.println(">>> proxy.getClass(): " + proxy.getClass()); //xxx.GreetingService$$EnhancerBySpringCGLIB$$xxx
		System.out.println(">>> proxy instanceof Proxy: " + ((Object)proxy instanceof java.lang.reflect.Proxy)); //false
		System.out.println(">>> proxy instanceof Proxy: " + ((Object)proxy instanceof org.springframework.cglib.proxy.Proxy)); //false
		System.out.println(">>> proxy instanceof SpringProxy: " + ((Object)proxy instanceof SpringProxy)); //true
		System.out.println(">>> AopUtils.isJdkDynamicProxy(proxy): " + AopUtils.isJdkDynamicProxy(proxy)); //false
		System.out.println(">>> AopUtils.isCglibProxy(proxy): " + AopUtils.isCglibProxy(proxy)); //true
		System.out.println(">>> AopUtils.getTargetClass(proxy): " + AopUtils.getTargetClass(proxy)); //
		target = (GreetingService1) AopProxyUtils.getSingletonTarget(proxy);
		System.out.println(">>> AopProxyUtils.getSingletonTarget(proxy): " + target); //
		System.out.println(">>> target.getClass(): " + target.getClass());
		
		target.sayHello();
		
		System.out.println("---------------------sayHello()-------------------");
		proxy.sayHello();
		System.out.println("---------------------sayWord()-------------------");
		proxy.sayWord("Spring AOP");
		System.out.println("---------------------sayBye-------------------");
		proxy.sayBye();
	}
	
	/**
	 * 该示例中,通知GreetingAdvice被应用到了目标GreetingService中的每个方法,包括Object的一些列方法(toString,equals等等)
	 */
	public static void testGreetingService2Proxy1() {
		ProxyFactory proxyFactory = new ProxyFactory();
		Object target = new GreetingService2Impl();
		proxyFactory.setTarget(target);
		proxyFactory.setInterfaces(GreetingService2.class);
		System.out.println(">>> target: " + target);
		proxyFactory.addAdvice(new GreetingAdvice());
		
		GreetingService2 proxy = (GreetingService2) proxyFactory.getProxy();
		System.out.println(">>> proxy.equals(target): " + proxy.equals(target)); //false, 在未重写equals方法的情况下,比的是内存地址,所以为false
		System.out.println(">>> proxy.getClass(): " + proxy.getClass()); //xxx.GreetingService$$EnhancerBySpringCGLIB$$xxx
		System.out.println(">>> proxy instanceof Proxy: " + ((Object)proxy instanceof java.lang.reflect.Proxy)); //false
		System.out.println(">>> proxy instanceof Proxy: " + ((Object)proxy instanceof org.springframework.cglib.proxy.Proxy)); //false
		System.out.println(">>> proxy instanceof SpringProxy: " + ((Object)proxy instanceof SpringProxy)); //true
		System.out.println(">>> AopUtils.isJdkDynamicProxy(proxy): " + AopUtils.isJdkDynamicProxy(proxy)); //false
		System.out.println(">>> AopUtils.isCglibProxy(proxy): " + AopUtils.isCglibProxy(proxy)); //true
		System.out.println(proxy); //toString()方法被代理了
		System.out.println("---------------------sayHello()-------------------");
		proxy.sayHello();
		System.out.println("---------------------sayWord()-------------------");
		proxy.sayWord("Spring AOP");
		System.out.println("---------------------sayBye-------------------");
		proxy.sayBye();
	}
	
	/**
	 * 该示例中,通知GreetingAdvice通过Pointcut被应用到了目标GreetingService中的某些方法
	 */
	public static void testGreetingService2Proxy2() {
		ProxyFactory proxyFactory = new ProxyFactory();
		Object target = new GreetingService2Impl();
		proxyFactory.setTarget(target);
		proxyFactory.setInterfaces(GreetingService2.class);
		
		System.out.println(">>> target: " + target);
		
		AbstractRegexpMethodPointcut regexMethodPointcut = new JdkRegexpMethodPointcut();
		regexMethodPointcut.setPattern(".*say.*");
		proxyFactory.addAdvisor(new DefaultPointcutAdvisor(regexMethodPointcut, new GreetingAdvice()));
		
		GreetingService2 proxy = (GreetingService2) proxyFactory.getProxy();
		System.out.println(">>> proxy.equals(target): " + proxy.equals(target)); //false, 在未重写equals方法的情况下,比的是内存地址,所以为false
		System.out.println(">>> proxy.getClass(): " + proxy.getClass()); //xxx.GreetingService$$EnhancerBySpringCGLIB$$xxx
		System.out.println(">>> proxy instanceof Proxy: " + ((Object)proxy instanceof java.lang.reflect.Proxy)); //false
		System.out.println(">>> proxy instanceof Proxy: " + ((Object)proxy instanceof org.springframework.cglib.proxy.Proxy)); //false
		System.out.println(">>> proxy instanceof SpringProxy: " + ((Object)proxy instanceof SpringProxy)); //true
		System.out.println(">>> AopUtils.isJdkDynamicProxy(proxy): " + AopUtils.isJdkDynamicProxy(proxy)); //false
		System.out.println(">>> AopUtils.isCglibProxy(proxy): " + AopUtils.isCglibProxy(proxy)); //true
		System.out.println("---------------------sayHello()-------------------");
		proxy.sayHello();
		System.out.println("---------------------sayWord()-------------------");
		proxy.sayWord("Spring AOP");
		System.out.println("---------------------sayBye-------------------");
		proxy.sayBye();
	}
	
	public static void main(String[] args) {
		//testGreetingService1Proxy1();
		testGreetingService1Proxy2();
		//testGreetingService2Proxy1();
		//testGreetingService2Proxy2();
	}
	
}
