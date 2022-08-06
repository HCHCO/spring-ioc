package com.reader;

import com.base.BeanDefinition;
import com.exception.BeansException;

public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);


    BeanDefinition getBeanDefinition(String beanName) throws BeansException;


    boolean containsBeanDefinition(String beanName);


    String[] getBeanDefinitionNames();
}
