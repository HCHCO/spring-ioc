package entity;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String,String> map = new HashMap<>();
//    static {
//        map.put("10001","a");
//        map.put("10002","b");
//        map.put("10003","c");
//        map.put("10004","d");
//    }

    public String queeryUserName(String uId){
        return map.get(uId);
    }
    //v8.0
    public void initDataMethod(){
        System.out.println("init-method");
            map.put("10001","a");
            map.put("10002","b");
            map.put("10003","c");
            map.put("10004","d");
    }
    public void destroyDataMethod(){
        System.out.println("destory method");
        map.clear();
    }

}
