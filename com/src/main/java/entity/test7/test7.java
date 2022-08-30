package entity.test7;

import entity.UserService;
import factory.reader.ClassPathXmlApplicationContext;

public class test7 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springa.xml");
        applicationContext.registerShutdownHook();
        UserServiceA userService = applicationContext.getBean("userServiceA",UserServiceA.class);
        String result = userService.queryUserInfo();
        System.out.println(result);
    }

}
