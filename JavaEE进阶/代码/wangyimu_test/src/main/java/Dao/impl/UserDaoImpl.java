package Dao.impl;

import Dao.UserDao;
import domain.User;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author wangyimu
 * @create 2021-12-30 14:00
 */
public class UserDaoImpl implements UserDao {
    private Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }


    /*private Map<String,User> userMap;

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }*/

    /* private List<User> userList;

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }*/

    @Override
    public void save() {
        /*System.out.println(userList);*/
        /*System.out.println(userMap);*/
        System.out.println(properties);
        System.out.println("UserDao save method running···");
    }
    /*private String company;
    private int age;

    public void setCompany(String company) {
        this.company = company;
    }

    public void setAge(int age) {
        this.age = age;
    }*/

    /*private List<String> strList;

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }

    @Override
    public void save() {
        System.out.println(strList);
    }*/




    /*public void save() {
        System.out.println(company + ":" + age);
        System.out.println("save-running····");
    }*/
}
