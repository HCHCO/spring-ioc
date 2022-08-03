package com.registry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{
    private Map<String,Object> beanDefinitionMap = new HashMap<>();
    @Override
    public Object getSingleton(String beanName) {
        return beanDefinitionMap.get(beanName);
    }
    protected  void addSingleton(String name,Object beanDefinition){
        beanDefinitionMap.put(name,beanDefinition);
    }
}
