package yimu.demo;

import yimu.dao.impl.UserDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import yimu.service.UserService;

/**
 * @author wangyimu
 * @Program Spring
 * @create 2022-02-19-12:09
 */
public class UserController {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("appc.xml");
        // UserService userService = (UserService) app.getBean("userService");
        UserService userService = app.getBean(UserService.class);
        userService.save();

        // 未从容器中获取数据
       /* UserService userService = new UserServiceImpl();
        userService.save();*/
    }
}
