package factory.factorybean;

import exception.BeansException;
import factory.FactoryBean;
import factory.registry.DefaultSingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {
    private final Map<String,Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectForFactoryBean(String beanName){
        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object !=NUL_OBJECT?object:null);
    }
    protected  Object getObjectFromFactoryBean(FactoryBean factory,String beanName){
            if(factory.isSingleton()){
                Object object = this.factoryBeanObjectCache.get(beanName);
                if(object==null){
                    object = doGetObjectFromFactoryBean(factory,beanName);
                    this.factoryBeanObjectCache.put(beanName,(object!=null?object:NUL_OBJECT));
                }
                return (object !=NUL_OBJECT ? object:null);
            }else {
                return doGetObjectFromFactoryBean(factory,beanName);
            }
    }
    private Object doGetObjectFromFactoryBean(final FactoryBean factory,final String beanName){
        try{
            return factory.getObject();
        }catch (Exception e){
            throw  new BeansException("");
        }
    }

}
