package entity.test8;

import entity.UserDao;
import exception.BeansException;
import factory.initializing.DisposableBean;
import factory.initializing.InitializingBean;

public class UserService8 implements InitializingBean, DisposableBean {
    // test 3
    private String uId;
    private UserDao userDao;
    private String company;
    private String location;

    public UserService8() {
    }

    public String queryUserInfo(){
        System.out.println("query"+uId+userDao.queeryUserName(uId));
        return  userDao.queeryUserName(uId)+"公司"+company+"地点+"+location;
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

    public UserService8(String uId, UserDao userDao, String company, String location) {
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
    // v8.0
    @Override
    public void destory() throws Exception {
        System.out.println("userservice8:destory method");
    }

    @Override
    public void afterPropertiesSet() throws BeansException {
        System.out.println("userservice8 do afterPropertiesSet");
    }
}
