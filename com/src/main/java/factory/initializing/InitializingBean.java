package factory.initializing;

import exception.BeansException;
/*
v8.0
 */
public interface InitializingBean {
    void afterPropertiesSet() throws BeansException;
}
