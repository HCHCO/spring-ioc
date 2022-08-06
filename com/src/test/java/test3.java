import bean.UserDao;
import bean.UserService;
import com.base.BeanDefinition;
import com.base.BeanReference;
import com.base.PropertyValue;
import com.base.PropertyValues;
import com.factory.DefaultListabeBeanFactory;
import org.junit.Test;


public class test3 {
    public static void main(String[] args) {
        DefaultListabeBeanFactory beanFactory = new DefaultListabeBeanFactory();
        beanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId","1001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));


        BeanDefinition beanDefinition = new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registerBeanDefinition("userService",beanDefinition);
        UserService userService = (UserService)beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
