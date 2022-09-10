package factory;

import bean.BeanDefinition;
import bean.BeanReference;
import bean.postprocessor.BeanPostProcessor;
import bean.property.PropertyValue;
import bean.property.PropertyValues;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import exception.BeansException;
import factory.config.AutowireCapableBeanFactory;
import factory.initializing.DisposableBean;
import factory.initializing.DisposableBeanAdapter;
import factory.initializing.InitializingBean;
import factory.instance.CglibSubclassingInstantionStrategy;
import factory.instance.InstantiationStrategy;
import factory.know.Aware;
import factory.know.BeanClassLoaderAware;
import factory.know.BeanFactoryAware;
import factory.know.BeanNameAware;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantionStrategy();
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) throws BeansException {
        // v9.0
        Object bean = null;
        try{
            // v2.0
            //    bean = beanDefinition.getBeanClass().newInstance();
            // v3.0 创建bean
            bean = createBeanInstance(beanDefinition,beanName,args);
            // v4.0 填充属性
            applyPropertyValues(beanName,bean,beanDefinition);
            // v7.0 前置和后置处理方法
            bean = initializeBean(beanName,bean,beanDefinition);
        }catch(Exception e){
            throw  new BeansException("instance failed",e);
        }
        // v8.0 注册实现了disposableBean 对象
        registerDisposableBeanIfNecessary(beanName,bean,beanDefinition);
        // v9.0
        if(beanDefinition.isSingleton()){
            addSingleton(beanName,bean);
        }
        return bean;
    }
    protected Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object... args){
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for(Constructor ctor:declaredConstructors){
            if(null!=args&&ctor.getParameterTypes().length==args.length){
                constructorToUse =ctor;
                break;
            }
        }
        return  getInstantiationStrategy().instantiate(beanDefinition,beanName,constructorToUse,args);

    }
    protected void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition){
        try{
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for(PropertyValue pv:propertyValues.getPropertyValues()){
                String name = pv.getName();
                Object value = pv.getValue();
                if(value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean,name,value);
            }
        }catch (Exception e){
            throw  new BeansException("error set property value");
        }
    }
    public InstantiationStrategy getInstantiationStrategy(){
        return  instantiationStrategy;
    }
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy){
        this.instantiationStrategy=instantiationStrategy;
    }

    // v7.0 initializeBean
    private  Object initializeBean(String beanName,Object bean,BeanDefinition beanDefinition){
        // v9.0
        if(bean instanceof Aware) {
            if(bean instanceof BeanFactoryAware){
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if(bean instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if(bean instanceof BeanNameAware){
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean,beanName);
        // v8.0
        try {
            invokeInitMethods(beanName,wrappedBean,beanDefinition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean,beanName);
        return  wrappedBean;

    }
    // v8.0
    private void invokeInitMethods(String beanName,Object wrappedBean,BeanDefinition beanDefinition)throws  Exception{
        // implements initializingBean
        if(wrappedBean instanceof InitializingBean){
            ((InitializingBean)wrappedBean).afterPropertiesSet();;
        }
        // 配置init-method
        String initMethodName = beanDefinition.getInitMethodName();
        if(StrUtil.isNotEmpty(initMethodName)){
            Method initMehtod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if(null==initMehtod){
                throw new BeansException("could not find init method ");
            }
            initMehtod.invoke(wrappedBean);
        }
    }
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName){
        Object result = existingBean;
        for(BeanPostProcessor processor: getBeanPostProcessors()){
            Object current = processor.postProcessBeforeInitialization(result,beanName);
            if(null==current) return  result;
            result = current;
        }
        return  result;
    }
    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName){
        Object result = existingBean;
        for(BeanPostProcessor processor: getBeanPostProcessors()){
            Object current = processor.postProcessBeforeInitialization(result,beanName);
            if(null==current) return  result;
            result = current;
        }
        return  result;
    }
    // v8.0
    protected  void registerDisposableBeanIfNecessary(String beanName,Object bean,BeanDefinition beanDefinition){
        // v9.0
        if(!beanDefinition.isSingleton()) return;
        if(bean instanceof DisposableBean|| StrUtil.isNotEmpty(beanDefinition.getDestoryMethodName())){
            registerDisposableBean(beanName,new DisposableBeanAdapter(bean,beanName,beanDefinition));
        }
    }



}
