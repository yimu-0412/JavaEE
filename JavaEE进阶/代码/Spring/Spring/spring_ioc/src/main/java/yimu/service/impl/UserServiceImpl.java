package yimu.service.impl;

import yimu.dao.UserDao;
import yimu.service.UserService;

/**
 * @author wangyimu
 * @Program Spring
 * @create 2022-02-19-12:05
 */
public class UserServiceImpl implements UserService {

    private UserDao us;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDao us) {
        this.us = us;
    }

    /*public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }*/

    public void save() {
        us.save();
    }
}