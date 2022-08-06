package com.base;

public class BeanReference {
    public String getName() {
        return beanName;
    }

    public void setName(String beanName) {
        this.beanName = beanName;
    }

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }
    public BeanReference() {
    }

    String beanName;

}
