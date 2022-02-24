package yimu.test;

import yimu.dao.UserDao;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wangyimu
 * @Program Spring
 * @create 2022-02-19-11:27
 */
public class SpringTest {
    @Test
    // 测试 scope 属性
    public void test1(){
        ClassPathXmlApplicationContext app = new
                ClassPathXmlApplicationContext("appc.xml");
        UserDao user1 = (UserDao) app.getBean("userDao");
        // UserDao user2 = (UserDao) app.getBean("userDao");
        System.out.println(user1);
        // System.out.println(user2);
        // app.close();
    }
}
