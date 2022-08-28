package entity.test5;

import bean.loader.DefaultResourceLoader;
import bean.loader.resource.Resource;
import cn.hutool.core.io.IoUtil;
import entity.UserService;
import factory.DefaultListableBeanFactory;
import factory.reader.XmlBeanDefinitionReader;

import java.io.IOException;
import java.io.InputStream;

public class test5 {
    public static void main(String[] args) throws IOException {
        // test v6.0 resource
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream intputStream = resource.getIntputStream();
        String content = IoUtil.readUtf8(intputStream);
        System.out.println("content"+content);
        Resource resource1 = resourceLoader.getResource("src/main/resources/important.properties");
        InputStream intputStream1 = resource1.getIntputStream();
        String content1 = IoUtil.readUtf8(intputStream1);
        System.out.println("content1"+content1);
        /*Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/blob/main/important.properties")
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);resourceLoader.getResource("url");
        */
        // test v6.0 bean
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinition("classpath:spring.xml");
        UserService userService =beanFactory.getBean("userService",UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("result"+result);
    }
}
