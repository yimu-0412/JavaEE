package dataTest.service.impl;

import dataTest.dao.UserDao;
import dataTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author wangyimu
 * @Program Spring_5
 * @create 2022-01-03-18:16
 */
// <bean id="service" class="dataTest.service.impl.UserServiceImpl">
//        <property name="userDao" ref="userDao"/>
//    </bean>
@Component("service")
//@Service("service")
@Scope("singleton")
public class UserServiceImpl implements UserService {

    /*@Autowired
    @Qualifier("userDao")*/
    @Resource(name="userDao")
    private UserDao userDao;

    @Value("注入普通数据")
    private String str;

    @Value("${jdbc.driver}")
    private String driver;

    /*public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }*/

    public void save() {
        System.out.println(str);
        System.out.println(driver);
        userDao.save();
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化方法···");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("销毁方法···");
    }
}
