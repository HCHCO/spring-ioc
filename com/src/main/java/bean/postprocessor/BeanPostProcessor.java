package bean.postprocessor;

import exception.BeansException;

/*
  v7.0

 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean,String name)throws BeansException;

    Object postProcessAfterInitialization(Object bean,String name)throws  BeansException;
}
