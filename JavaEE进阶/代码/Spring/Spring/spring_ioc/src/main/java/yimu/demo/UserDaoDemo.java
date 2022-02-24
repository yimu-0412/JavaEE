package yimu.demo;

import yimu.dao.UserDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wangyimu
 * @Program Spring
 * @create 2022-02-19-11:15
 */
public class UserDaoDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext app = new
                ClassPathXmlApplicationContext("appc.xml");
        UserDao userDao = (UserDao) app.getBean("userDao");
        userDao.save();
    }

}
