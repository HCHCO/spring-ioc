package entity.test6;

import entity.UserService;
import factory.DefaultListableBeanFactory;
import factory.reader.ClassPathXmlApplicationContext;
import factory.reader.XmlBeanDefinitionReader;

public class test {
    public static void main(String[] args) {
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
//        reader.loadBeanDefinition("classpath:spring.xml");
//        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
//        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
//        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
//        beanFactory.addBeanPostProcessor(beanPostProcessor);
//        UserService userService = beanFactory.getBean("userService",UserService.class);
//        String result =userService.queryUserInfo();
//        System.out.println(result);
        // test context
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");
        UserService userService1 = applicationContext.getBean("userService",UserService.class);
        String result1 = userService1.queryUserInfo();
        System.out.println(result1);
    }
}
