package dataTest.web;

import dataTest.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wangyimu
 * @Program Spring_5
 * @create 2022-01-03-18:21
 */
public class UserController {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("appl.xml");
        UserService service = app.getBean(UserService.class);
        service.save();
        app.close();
    }
}
