package factory;

import bean.BeanDefinition;
import bean.postprocessor.BeanPostProcessor;
import cn.hutool.core.util.ClassUtil;
import exception.BeansException;
import factory.config.BeanFactory;
import factory.config.ConfigurableBeanFactory;
import factory.registry.DefaultSingletonBeanRegistry;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

public  abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    // v7.0
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();
    // v9.0
    private  ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    @Override
    public Object getBean(String name) throws BeansException {
        // SingletonBeanRegistry->DefaultSingletonBeanRegistry-> getSingleton
        return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name,args);
    }
    // v6.0
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }
    protected <T> T doGetBean(final String name,final Object[] args){
        Object bean = getSingleton(name);
        if(bean!=null){
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return  (T) createBean(name,beanDefinition,args);
    }

    protected abstract BeanDefinition getBeanDefinition(String name)throws BeansException;
    // v3.0
    // protected abstract Object createBean(String beanName,BeanDefinition beanDefinition) throws BeansException;
    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args) throws BeansException;
    // v7.0
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }
    public List<BeanPostProcessor> getBeanPostProcessors(){
        return  this.beanPostProcessors;
    }
    // v9.0
    public  ClassLoader getBeanClassLoader(){
        return  this.beanClassLoader;
    }

}
