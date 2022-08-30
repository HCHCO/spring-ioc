package entity.test7;

import entity.UserDao;
import exception.BeansException;
import factory.config.BeanFactory;
import factory.context.ApplicationContext;
import factory.initializing.DisposableBean;
import factory.initializing.InitializingBean;
import factory.know.ApplicationContextAware;
import factory.know.BeanClassLoaderAware;
import factory.know.BeanFactoryAware;
import factory.know.BeanNameAware;

public class UserServiceA implements BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware{
    // test 3
    private String uId;
    private UserDao userDao;
    private String company;
    private String location;

    // v9.0
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    public UserServiceA() {
    }

    public String queryUserInfo(){
        System.out.println("UserService query method:"+"uId:"+uId+userDao.queeryUserName(uId));
        return  userDao.queeryUserName(uId);
    }

    public UserServiceA(String uId, UserDao userDao, String company, String location, ApplicationContext applicationContext, BeanFactory beanFactory) {
        this.uId = uId;
        this.userDao = userDao;
        this.company = company;
        this.location = location;
        this.applicationContext = applicationContext;
        this.beanFactory = beanFactory;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserServiceA(String uId, UserDao userDao, String company, String location) {
        this.uId = uId;
        this.userDao = userDao;
        this.company = company;
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext=applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("classLoader:"+classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

        this.beanFactory= beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("beanName+"+name);
    }
}
