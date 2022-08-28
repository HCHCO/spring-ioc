package bean;

import bean.property.PropertyValue;
import bean.property.PropertyValues;

import java.awt.*;

//public class BeanDefinition {
//    private Object bean;
//    public BeanDefinition(Object bean){
//        this.bean=bean;
//
//    }
//    public Object getBean(){
//        return bean;
//    }
//}
public class BeanDefinition{
    private Class beanClass;
    // v4.0
    private PropertyValues propertyValues;
    public BeanDefinition(Class beanClass){
        this.beanClass=beanClass;
        this.propertyValues = new PropertyValues();
    }
    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues!= null?propertyValues : new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

}
