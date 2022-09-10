package entity;

import bean.BeanDefinition;
import bean.BeanReference;
import bean.property.PropertyValue;
import bean.property.PropertyValues;
import entity.test8.UserService8;
import factory.DefaultListableBeanFactory;
import factory.reader.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {
        // test1
//        BeanFactory beanFactory = new BeanFactory();
//        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
//        beanFactory.registerBeanDefinition("userService",beanDefinition);
//        UserService  userService =(UserService)beanFactory.getBean("userService");
//        userService.queryUserInfo();
//        //test2
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
//        beanFactory.registerBeanDefinition("userService",beanDefinition);
//        UserService userService = (UserService) beanFactory.getBean("userService");
//        userService.queryUserInfo();
//        // 第二次获取
//        UserService userService1 = (UserService) beanFactory.getBean("userService");
//        userService1.queryUserInfo();
//        System.out.println(userService==userService1);
//        // test 3
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        BeanDefinition beanDefinition= new BeanDefinition(UserService.class);
//        beanFactory.registerBeanDefinition("userService",beanDefinition);
//        UserService service = (UserService)  beanFactory.getBean("userService","hello");
//        service.queryUserInfo();
        //test 4
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPorpertyValue(new PropertyValue("uId","10001"));
        propertyValues.addPorpertyValue(new PropertyValue("userDao",new BeanReference("userDao")));
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registerBeanDefinition("userService",beanDefinition);
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        //
        //System.out.println(ClassLayout.parseInstance(userService).toPrintable());
    }
}
