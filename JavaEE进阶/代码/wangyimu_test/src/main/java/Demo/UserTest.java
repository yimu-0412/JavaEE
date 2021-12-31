package Demo;

import Dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wangyimu
 * @create 2021-12-30 14:04
 */
public class UserTest {
    public static void main(String[] args) {
        ApplicationContext app = new
                ClassPathXmlApplicationContext("app.xml");
        UserDao ud1 = (UserDao)app.getBean("ud");
        UserDao ud2 = (UserDao)app.getBean("ud");
//        System.out.println(ud1);
//        System.out.println(ud2);
        ud1.save();
        ud2.save();
    }
}
