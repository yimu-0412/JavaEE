package yimu.service.impl;

import yimu.dao.UserDao;
import yimu.service.UserService;

/**
 * @author wangyimu
 * @Program Spring
 * @create 2022-02-20-0:44
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save() {
        userDao.save();
    }
}
