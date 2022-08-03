package com.proxy;

import com.base.BeanDefinition;
import com.exception.BeansException;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args)throws BeansException;
}
