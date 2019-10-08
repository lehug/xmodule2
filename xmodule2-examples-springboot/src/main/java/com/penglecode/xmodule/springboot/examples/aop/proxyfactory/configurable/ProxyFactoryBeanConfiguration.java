package com.penglecode.xmodule.springboot.examples.aop.proxyfactory.configurable;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.penglecode.xmodule.springboot.examples.aop.SimpleLoggingAdvice;

/**
 * ProxyFactoryBean的使用
 * 
 * @author 	pengpeng
 * @date	2019年9月29日 上午11:18:09
 */
@Configuration
@ConditionalOnProperty(prefix="springboot.examples", name="active", havingValue="aop.proxyfactory.configurable", matchIfMissing=false)
public class ProxyFactoryBeanConfiguration {

	@Bean
	public MessageService messageService() {
		return new MessageServiceImpl();
	}
	
	@Bean
	public SimpleLoggingAdvice messageServiceAdvice() {
		return new SimpleLoggingAdvice();
	}
	
	/**
	 * 因为SpringBoot默认启用了#AnnotationAwareAspectJAutoProxyCreator，它会自动应用该messageServiceAdvisor
	 * 此时下面配置的ProxyFactoryBean被注册到容器后，将会产生二次重复代理的问题，即拦截的逻辑被执行了两次(见打印日志)
	 * 
	 * 如果注册了messageServiceAdvisor，则在运行该示例的时候，请关闭SpringBoot默认的AOP配置：spring.aop.auto=false
	 * 
	 * 否则请在ProxyFactoryBean装配的时候手动设置Advisor: proxyFactoryBean.addAdvisor(new RegexpMethodPointcutAdvisor(".*send.*", messageServiceAdvice));
	 * @param messageServiceAdvice
	 * @return
	 */
	/*@Bean
	public RegexpMethodPointcutAdvisor messageServiceAdvisor(SimpleLoggingAdvice messageServiceAdvice) {
		return new RegexpMethodPointcutAdvisor(".*send.*", messageServiceAdvice);
	}*/
	
	/**
	 * SpringBoot默认启用了#AnnotationAwareAspectJAutoProxyCreator，它会自动应用上面的messageServiceAdvisor
	 * 此时下面配置的ProxyFactoryBean被注册到容器后，将会产生二次重复代理的问题，即拦截的逻辑被执行了两次(见打印日志)
	 * 
	 * @param messageService
	 * @return
	 */
	@Bean
	public ProxyFactoryBean messageServiceProxy1(MessageService messageService, SimpleLoggingAdvice messageServiceAdvice) {
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setTarget(messageService);
		//proxyFactoryBean.setInterceptorNames("messageServiceAdvisor");
		proxyFactoryBean.addAdvisor(new RegexpMethodPointcutAdvisor(".*send.*", messageServiceAdvice));
		return proxyFactoryBean;
	}
	
	@Bean
	public ProxyFactoryBeanExample messageServiceProxyExample() {
		return new ProxyFactoryBeanExample();
	}
	
}
