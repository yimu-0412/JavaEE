package yimuTest.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import yimuTest.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import yimuTest.service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author wangyimu
 * @Program Spring_5
 * @create 2022-01-02-17:17
 */

/*<bean id="us" class="yimuTest.service.impl.UserServiceImpl">*/
// @Component("us")
@Service("us")
@Scope("prototype")
//@Scope("sinfleton")
public class UserServiceImpl implements UserService {
    @Value("${jdbc.driver}")
    private String driver;

    // <property name="userDao" ref="userDao"></property>
    //@Autowired // 按照数据类型从 Spring 容器中进行匹配的
    //@Qualifier("userDao") // 是按照 id 值从容器中进行匹配的，但是此处要结合 @Autowired 一起使用
    @Resource(name="userDao") // @Resource 相当于 @Autowired + @Qualifier
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save() {
        System.out.println(driver);
        userDao.save();
    }

    @PostConstruct
    public void init(){
        System.out.println("Service 对象的初始化方法");
    }

    @PreDestroy
    public void destory(){
        System.out.println("Service 对象的销毁方法");
    }

}
