package com.reader;

import com.exception.BeansException;
import com.resource.Loader.ResourceLoader;
import com.resource.Resource;

public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource)throws BeansException;

    void loadBeanDefinitions(Resource...resources)throws BeansException;

    void loadBeanDefinitions(String location)throws  BeansException;
}
