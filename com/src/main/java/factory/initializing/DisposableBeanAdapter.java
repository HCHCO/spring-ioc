package factory.initializing;

import bean.BeanDefinition;
import cn.hutool.core.util.StrUtil;
import exception.BeansException;

import java.lang.reflect.Method;

public class DisposableBeanAdapter implements DisposableBean{
    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestoryMethodName();

    }

    @Override
    public void destory() throws Exception {
        // 实现disposableBean
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destory();
        }
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if(null==destroyMethod){
                throw new BeansException("not find destroy method naem "+destroyMethodName);
            }
            destroyMethod.invoke(bean);
        }
    }

}
