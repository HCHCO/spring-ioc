package bean;

public class UserService {
    private String uId;

    private UserDao userDao;

    public void queryUserInfo(){
        System.out.println("query User info"+userDao.queryUserName(uId));
    }
}
