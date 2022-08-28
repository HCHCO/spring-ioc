package entity.test6;

import bean.postprocessor.BeanPostProcessor;
import entity.UserService;
import exception.BeansException;

class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("userService".equals(beanName)){
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
