package com.context;

import com.exception.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext{
    // important 刷新容器
    void refresh() throws BeansException;

    //
    void registerShutdownHook();

    //
    void  close();
}
