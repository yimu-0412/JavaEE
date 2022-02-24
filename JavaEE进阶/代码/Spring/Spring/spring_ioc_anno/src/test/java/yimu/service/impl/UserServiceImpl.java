package yimu.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import yimu.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import yimu.service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * @author wangyimu
 * @Program Spring
 * @create 2022-02-19-18:29
 */
// <bean id="userService" class="yimu.service.impl.UserServiceImpl">
// @Component("userService")
@Service("userService")
// @Scope("singleton")
public class UserServiceImpl implements UserService {

    @Value("${jdbc.driver}")
    private String driver;


    // <property name="userDao" ref="userDao"></property>
    @Autowired // 按照类型从 spring 容器中进行匹配的
    @Qualifier("userDao") // 是根据 id值从容器中进行匹配的，但是注意要结合 @Autowired 一起使用
    //@Resource(name="userDao")
    private UserDao userDao;

    /*public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }*/

    public void save() {
        System.out.println(driver);
        userDao.save();
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化方法！");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("销毁方法！");
    }
}
