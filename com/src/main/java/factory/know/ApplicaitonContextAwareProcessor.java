package factory.know;

import bean.postprocessor.BeanPostProcessor;
import exception.BeansException;
import factory.context.ApplicationContext;

public class ApplicaitonContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicaitonContextAwareProcessor(ApplicationContext applicaitonContext) {
        this.applicationContext = applicaitonContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
        if(bean instanceof  ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return  bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
        return bean;
    }
}
