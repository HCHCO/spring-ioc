package factory.reader;

import bean.loader.ResourceLoader;
import bean.loader.resource.Resource;
import exception.BeansException;
import factory.registry.BeanDefinitionRegistry;

import java.beans.Beans;

public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinition(Resource resource) throws BeansException;

    void loadBeanDefinition(Resource...resources) throws BeansException;

    void  loadBeanDefinition(String location) throws BeansException;

}
