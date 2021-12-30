package service.impl;

import Dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

/**
 * @author wangyimu
 * @Program wangyimu_test
 * @create 2021-12-30-21:19
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /*public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }*/

    /*public UserServiceImpl() {
    }*/

    @Override
    public void save() {
       /*userDao.save();*/
        ApplicationContext app = new
                ClassPathXmlApplicationContext("app.xml");
        UserDao userDao = (UserDao)app.getBean("ud");
        userDao.save();
    }
}
