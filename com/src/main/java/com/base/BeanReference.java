package com.base;

public class BeanReference {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BeanReference(String name) {
        this.name = name;
    }
    public BeanReference() {
    }

    String name;

}
