package com.base;

import com.exception.BeansException;

public interface BeanPostProcessor {
    // before bean init
    Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException;
    // after bean init
    Object postProcessAfterInitialization(Object bena,String beanName) throws BeansException;
}
