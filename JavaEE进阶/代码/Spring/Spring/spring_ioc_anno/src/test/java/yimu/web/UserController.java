package yimu.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import yimu.config.SpringConfiguration;
import yimu.service.UserService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wangyimu
 * @Program Spring
 * @create 2022-02-19-18:52
 */
public class UserController {
    public static void main(String[] args) throws SQLException {
        /*ClassPathXmlApplicationContext app = new
                ClassPathXmlApplicationContext("appc.xml");*/
        /*ApplicationContext app = new
                ClassPathXmlApplicationContext("appc.xml");*/

        ApplicationContext app = new
                AnnotationConfigApplicationContext(SpringConfiguration.class);

        UserService userService = (UserService) app.getBean("userService");
        userService.save();
        // app.close();

        DataSource dataSource = (DataSource) app.getBean("dataSource");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
