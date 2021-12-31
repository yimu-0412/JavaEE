package Dao.impl;

import Dao.UserDao;

/**
 * @author wangyimu
 * @create 2021-12-30 16:04
 */
public class StaticFactoryBean {
    public static UserDao creatEUserDao(){
        return new UserDaoImpl();
    }
}
