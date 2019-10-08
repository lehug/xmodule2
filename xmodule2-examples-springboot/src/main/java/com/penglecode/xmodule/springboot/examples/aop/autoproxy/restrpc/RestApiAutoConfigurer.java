package com.penglecode.xmodule.springboot.examples.aop.autoproxy.restrpc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.penglecode.xmodule.common.util.CollectionUtils;

public class RestApiAutoConfigurer implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware, InitializingBean {

	private ApplicationContext applicationContext;
	
	private Set<String> basePackages;
	
	private List<Class<?>> basePackageClasses;
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// left intentionally blank
	}


	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		RestApiClassPathScanner scanner = new RestApiClassPathScanner(registry);
		scanner.setResourceLoader(applicationContext);
		scanner.registerFilters();
		scanner.scan(basePackages.toArray(new String[0]));
	}

	protected void mergeBasePackages() {
		if(basePackages == null) {
			basePackages = new HashSet<String>();
		}
		if(!CollectionUtils.isEmpty(basePackageClasses)) {
			for(Class<?> clazz : basePackageClasses) {
				basePackages.add(clazz.getPackage().getName());
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		mergeBasePackages();
	}

	protected ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public Set<String> getBasePackages() {
		return basePackages;
	}

	public void setBasePackages(Set<String> basePackages) {
		this.basePackages = basePackages;
	}

	public List<Class<?>> getBasePackageClasses() {
		return basePackageClasses;
	}

	public void setBasePackageClasses(List<Class<?>> basePackageClasses) {
		this.basePackageClasses = basePackageClasses;
	}

}
