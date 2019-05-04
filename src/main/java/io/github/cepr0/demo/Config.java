package io.github.cepr0.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanDefinitionRegistry(final BeanDefinitionRegistry registry) throws BeansException {
		GenericBeanDefinition gbd = new GenericBeanDefinition();
		gbd.setBeanClass(ManualBean.class);
		gbd.setAutowireCandidate(true);
		gbd.setScope("prototype");
		registry.registerBeanDefinition("manualB", gbd);
	}

	@Override
	public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}
}
