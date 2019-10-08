package com.penglecode.xmodule.springboot.examples.aop.autoproxy.simple;

import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.penglecode.xmodule.springboot.examples.aop.SimpleLogging;
import com.penglecode.xmodule.springboot.examples.aop.SimpleLoggingAdvice;

/**
 * 自动代理，同一个Spring上下文容器中只有有唯一的一个#AbstractAutoProxyCreator
 * 
 * https://www.iteye.com/blog/jinnianshilongnian-1894465
 * 
 * SpringBoot默认启用了#AnnotationAwareAspectJAutoProxyCreator，因此容器中就不需要重复定义其他#AbstractAutoProxyCreator(诸如：#BeanNameAutoProxyCreator, #DefaultAdvisorAutoProxyCreator)
 * 否则就会出现重复代理的问题
 * 
 * @author 	pengpeng
 * @date	2019年9月29日 上午10:50:04
 */
@Configuration
@ConditionalOnProperty(prefix="springboot.examples", name="active", havingValue="aop.autoproxy.simple", matchIfMissing=false)
public class AutoProxyConfiguration {

	@Bean
	public SimpleLoggingAdvice accountServiceAdvice() {
		return new SimpleLoggingAdvice();
	}
	
	@Bean
	public DefaultPointcutAdvisor accountServiceAdvisor(SimpleLoggingAdvice accountServiceAdvice) {
		AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(Service.class, SimpleLogging.class);
		return new DefaultPointcutAdvisor(pointcut, accountServiceAdvice);
	}
	
	/**
	 * 在启用springboot默认的AOP自动配置情况下，即spring.aop.auto=true的情况下，该BeanNameAutoProxyCreator不需要注册了，如果注册了，则accountService会出现二次重复代理
	 * @return
	 */
	/*@Bean
	public BeanNameAutoProxyCreator accountServiceBeanAutoProxyCreator() {
		BeanNameAutoProxyCreator autoProxyCreator = new BeanNameAutoProxyCreator();
		autoProxyCreator.setBeanNames("accountService");
		//autoProxyCreator.setInterceptorNames("accountServiceAdvisor");
		return autoProxyCreator;
	}*/
	
	@Bean
	public AutoProxyExample autoProxyExample() {
		return new AutoProxyExample();
	}
	
}
