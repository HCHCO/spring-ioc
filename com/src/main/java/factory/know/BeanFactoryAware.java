package factory.know;

import exception.BeansException;
import factory.config.BeanFactory;
import factory.know.Aware;

public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory)throws BeansException;
}
