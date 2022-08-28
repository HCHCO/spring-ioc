package entity;

public class UserService {
    // test 3
    private String uId;
    private UserDao userDao;
    private String company;
    private String location;

    public UserService() {
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

    public UserService(String uId, UserDao userDao, String company, String location) {
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
}
