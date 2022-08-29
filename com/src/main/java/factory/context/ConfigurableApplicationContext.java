package factory.context;

import exception.BeansException;
/*
    v7.0

 */
public interface ConfigurableApplicationContext extends ApplicationContext{
    void refresh() throws BeansException;

    // v8.0
    void registerShutdownHook();

    void close();
}
