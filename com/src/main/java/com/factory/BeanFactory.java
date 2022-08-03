package com.factory;

import com.base.BeanDefinition;
import com.exception.BeansException;

import java.util.HashMap;
import java.util.Map;

public interface BeanFactory {
    Map<String, BeanDefinition> BeanDefinitionMap = new HashMap<>();
    Object getBean(String name) throws BeansException;
    Object getBean(String name,Object...args)throws BeansException;
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition);
}
