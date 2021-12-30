package Demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

/**
 * @author wangyimu
 * @Program wangyimu_test
 * @create 2021-12-30-21:27
 */
public class UserController {
    public static void main(String[] args) {
        ApplicationContext app = new
                ClassPathXmlApplicationContext("app.xml");
        UserService service =(UserService) app.getBean("us");
        service.save();
    }
}
