package entity.test7;

import entity.UserService;
import factory.reader.ClassPathXmlApplicationContext;

public class test7 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        UserService userService = applicationContext.getBean("userService",UserService.class);
        String result = userService.queryUserInfo();
        System.out.println(result);
    }

}
