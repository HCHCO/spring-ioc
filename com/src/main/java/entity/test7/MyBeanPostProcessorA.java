package entity.test7;

import bean.postprocessor.BeanPostProcessor;
import entity.UserService;
import exception.BeansException;

class MyBeanPostProcessorA implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("userServiceA".equals(beanName)){
            UserService userService =(UserService) bean;
            userService.setLocation("changeLocation");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
        return bean;
    }
}
