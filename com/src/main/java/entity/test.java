package entity;

import entity.test8.UserService8;
import factory.reader.ClassPathXmlApplicationContext;

public class test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring8.xml");
        applicationContext.registerShutdownHook();
        UserService8 userService8 = applicationContext.getBean("userService8",UserService8.class);
        UserService8 userService = applicationContext.getBean("userService8",UserService8.class);
        System.out.println(userService);
        System.out.println(userService8);
        System.out.println(userService + " 十六进制哈希：" + Integer.toHexString(userService.hashCode()));
    }
}
