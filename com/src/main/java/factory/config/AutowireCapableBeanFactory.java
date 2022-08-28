package factory.config;

import exception.BeansException;

public interface AutowireCapableBeanFactory extends BeanFactory{
    // v7.0
    Object applyBeanPostProcessorsBeforeInitialization(Object exsitingBean,String beanName)throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object exsitingBean,String beanName)throws BeansException;
}
