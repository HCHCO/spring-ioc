package factory.registry;

import exception.BeansException;
import factory.initializing.DisposableBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private final Map<String,Object> singletonObjects = new HashMap<>();

    //v8.0
    private final Map<String,DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }
    protected void addSingleton(String name,Object singletonObject){
        singletonObjects.put(name,singletonObject);
    }
    // v8.0
    public void registerDisposableBean(String beanName, DisposableBean bean){
        disposableBeans.put(beanName,bean);
    }
    public void destroySingletons(){
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();
        for(int i =disposableBeanNames.length-1;i>=0;i--){
            Object beanName  = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try{
                disposableBean.destory();
            }catch (Exception e){
                throw new BeansException("destory method with "+beanName);
            }
        }

    }
}
