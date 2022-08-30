package entity.test7;

import bean.BeanDefinition;
import bean.postprocessor.BeanFactoryPostProcessor;
import bean.property.PropertyValue;
import bean.property.PropertyValues;
import exception.BeansException;
import factory.ConfigurableListableBeanFactory;

class MyBeanFactoryPostProcessorA implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userServiceA");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPorpertyValue(new PropertyValue("company","new Company"));
    }
}