package bean.postprocessor;

import exception.BeansException;
import factory.ConfigurableListableBeanFactory;

/*
v7.0
implements contexts add
 */
public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
