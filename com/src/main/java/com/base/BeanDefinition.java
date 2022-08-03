package com.base;

public class BeanDefinition {
    private Class BeanClass;

    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        BeanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        BeanClass = beanClass;
        this.propertyValues = propertyValues;
    }

    public Class getBeanClass() {
        return BeanClass;
    }

    public void setBeanClass(Class beanClass) {
        BeanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
