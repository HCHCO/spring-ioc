package factory.config;

import bean.postprocessor.BeanPostProcessor;
import factory.config.HierarchicalBeanFactory;
import factory.registry.SingletonBeanRegistry;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON ="singleton";
    String SCOPE_PROTOTYPE ="prototype";
    // v7.0
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
