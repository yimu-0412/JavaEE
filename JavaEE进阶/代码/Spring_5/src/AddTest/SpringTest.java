package AddTest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.User;

/**
 * @author wangyimu
 * @Program Spring_5
 * @create 2021-12-27-22:41
 */
public class SpringTest {
    @Test
    public void testAdd(){
        // 1.加载 Spring 配置文件
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean1.xml");
        // 2.获取配置创建的对象
        User user = context.getBean("user", User.class);
        System.out.println(user);
        user.add();
    }
}
