package factory;

import bean.BeanDefinition;
import exception.BeansException;
import factory.registry.BeanDefinitionRegistry;

import java.beans.Beans;
import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry,ConfigurableListableBeanFactory{
    private final Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();
    @Override
    public BeanDefinition getBeanDefinition(String name) throws BeansException {
        BeanDefinition beanDefinition=beanDefinitionMap.get(name);
        if(beanDefinition==null)throw new BeansException("no bean name");
        return  beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name,beanDefinition);
    }
    // v6.0
    @Override
    public boolean containsBeanDefinition(String beanName){
        return  beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public <T> Map<String,T> getBeansOfType(Class<T> type) throws BeansException{
        Map<String,T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName,beanDefinition)->{
          Class beanClass = beanDefinition.getBeanClass();
          if(type.isAssignableFrom(beanClass)){
              result.put(beanName,(T) getBean(beanName));
          }
        });
        return result;
    }

    // v7.0
    @Override
    public void preInstantiateSingletons() throws BeansException{
        beanDefinitionMap.keySet().forEach(this::getBean);
    }
}
