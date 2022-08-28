package factory.config;


import bean.BeanDefinition;
import exception.BeansException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
// v1.0
//public class BeanFactory {
//    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
//    public Object getBean(String name){
//        return  beanDefinitionMap.get(name).getBean();
//    }
//    public void registerBeanDefinition(String name,BeanDefinition beanDefinition){
//        beanDefinitionMap.put(name,beanDefinition);
//    }
//}
// v2.0
//public interface BeanFactory{
//    Object getBean(String name) throws BeansException;
//}
public interface BeanFactory {
    Object getBean(String name) throws BeansException;
    // v3.0
    Object getBean(String name,Object... args)throws BeansException;
    // v6.0
    <T> T getBean(String name,Class<T> requiredType) throws BeansException;
}