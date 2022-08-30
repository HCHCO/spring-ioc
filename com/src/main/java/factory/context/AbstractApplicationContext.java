package factory.context;

import bean.loader.DefaultResourceLoader;
import bean.postprocessor.BeanFactoryPostProcessor;
import bean.postprocessor.BeanPostProcessor;
import exception.BeansException;
import factory.ConfigurableListableBeanFactory;
import factory.know.ApplicaitonContextAwareProcessor;

import java.util.Map;
/*
    v7.0

 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    @Override
    public void refresh() throws BeansException {
        // 创建beanFactory,并且加载BeanDefinition
        refreshBeanFactory();
        // 获取beanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // v9.0 添加 ApplicationContextAwareProcessor，让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicaitonContextAwareProcessor(this));
        //在bean实例化前beanfactorypostprocessor
        invokeBeanFactoryPostProcessors(beanFactory);
        //注册
        registerBeanPostProcessor(beanFactory);
        //实例化
        beanFactory.preInstantiateSingletons();
    }
    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for(BeanFactoryPostProcessor beanFactoryPostProcessor:beanFactoryPostProcessorMap.values()){
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessor(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for(BeanPostProcessor beanPostProcessor:beanFactoryPostProcessorMap.values()){
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    //v8.0
    @Override
    public void registerShutdownHook(){
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }
    @Override
    public void close(){
        getBeanFactory().destroySingletons();
    }

}
