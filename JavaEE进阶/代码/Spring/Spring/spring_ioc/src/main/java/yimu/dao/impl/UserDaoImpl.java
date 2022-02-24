package yimu.dao.impl;

import yimu.dao.UserDao;
import yimu.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author wangyimu
 * @Program Spring
 * @create 2022-02-19-10:44
 */
public class UserDaoImpl implements UserDao{

    private List<String> listStr;
    private Map<String, User> userMap;
    private Properties properties;


    private String username;
    private int age;

    public void setListStr(List<String> listStr) {
        this.listStr = listStr;
    }

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

   /* public UserDaoImpl() {
        System.out.println("UserDaoImpl 创建！");
    }*/

    public void save() {
        // System.out.println(username + "===" + age);

        System.out.println(listStr);
        System.out.println(userMap);
        System.out.println(properties);

        System.out.println("save running ·····");
    }

    /*public void init(){
        System.out.println("初始化方法！");
    }

    public void destory(){
        System.out.println("销毁方法！");
    }*/
}
