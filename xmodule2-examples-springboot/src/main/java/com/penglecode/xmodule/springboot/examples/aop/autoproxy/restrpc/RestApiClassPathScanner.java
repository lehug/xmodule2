package com.penglecode.xmodule.springboot.examples.aop.autoproxy.restrpc;

import java.util.Arrays;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.penglecode.xmodule.common.util.ClassUtils;
import com.penglecode.xmodule.common.util.StringUtils;

public class RestApiClassPathScanner extends ClassPathBeanDefinitionScanner {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestApiClassPathScanner.class);
	
	public RestApiClassPathScanner(BeanDefinitionRegistry registry) {
		super(registry, false);
		setBeanNameGenerator(new RestApiBeanNameGenerator());
	}

	protected void registerFilters() {
		addIncludeFilter(new AnnotationTypeFilter(RestApi.class));
	}

	@Override
	protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
		Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
		if (beanDefinitions.isEmpty()) {
			LOGGER.warn(
					"No rest api was found in '{}' package which annotated with @RestApi. Please check your configuration.",
					Arrays.toString(basePackages));
		} else {
			processBeanDefinitions(beanDefinitions);
		}
		return beanDefinitions;
	}

	/**
	 * 重写，使得能够扫描到@RestApi注解的接口
	 */
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
	}

	protected void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
		GenericBeanDefinition definition;
		for (BeanDefinitionHolder holder : beanDefinitions) {
			definition = (GenericBeanDefinition) holder.getBeanDefinition();
			String beanClassName = definition.getBeanClassName();
			definition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
			definition.setBeanClass(RestApiFactoryBean.class);
			definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
		}
	}
	
	private static class RestApiBeanNameGenerator implements BeanNameGenerator {

		private BeanNameGenerator defaultBeanNameGenerator = new AnnotationBeanNameGenerator();
		
		@Override
		public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
			String beanClassName = definition.getBeanClassName();
			String beanName = getBeanName(ClassUtils.forName(beanClassName));
			return StringUtils.isEmpty(beanName) ? defaultBeanNameGenerator.generateBeanName(definition, registry) : beanName;
		}
	
		private String getBeanName(Class<?> beanClass) {
			RestApi restApi = beanClass.getAnnotation(RestApi.class);
			return restApi.clientName();
		}
		
	}

}