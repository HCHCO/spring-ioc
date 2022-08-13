package com.base;

import com.exception.BeansException;
import com.factory.*;
import com.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    // 在所有beanDefenition 加载完成后,实例化Bean 对象之前，
    // 提供修改BeanDefenition 属性机制

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFacotry) throws BeansException;
}
