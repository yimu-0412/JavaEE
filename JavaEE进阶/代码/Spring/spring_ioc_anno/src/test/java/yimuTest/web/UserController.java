package yimuTest.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import yimuTest.service.UserService;

/**
 * @author wangyimu
 * @Program Spring_5
 * @create 2022-01-02-17:22
 */
public class UserController {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext app = new
                ClassPathXmlApplicationContext("applicationContext.xml");
//      UserService service =(UserService)app.getBean("us");
        UserService service = app.getBean(UserService.class);
        service.save();
        app.close();
    }
}
