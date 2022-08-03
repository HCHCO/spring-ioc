package com.factory;

import com.base.BeanDefinition;
import com.exception.BeansException;

import java.util.HashMap;
import java.util.Map;

public class DefaultListabeBeanFactory extends AbstractAutowireCapableBeanFactory{
    private Map<String,BeanDefinition> beanDefinitionMap =new HashMap<>();
    @Override
    public void registerBeanDefinition(String name,BeanDefinition beanDefinition){
        beanDefinitionMap.put(name,beanDefinition);
    }
    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        return null;
    }
}
