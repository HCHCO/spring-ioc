package com.factory;

import com.base.BeanDefinition;
import com.base.BeanPostProcessor;
import com.exception.BeansException;
import com.factory.config.AutowireCapableBeanFactory;
import com.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
    void preInstantiateSingletons() throws BeansException;
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
