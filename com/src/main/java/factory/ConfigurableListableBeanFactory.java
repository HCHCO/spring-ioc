package factory;

import bean.BeanDefinition;
import bean.postprocessor.BeanPostProcessor;
import exception.BeansException;
import factory.config.AutowireCapableBeanFactory;
import factory.config.ConfigurableBeanFactory;
import factory.config.ListableBeanFactory;

import java.beans.Beans;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String name) throws BeansException;
    // v7.0
    void preInstantiateSingletons() throws BeansException;
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
