package dataTest.dao.impl;

import dataTest.dao.UserDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author wangyimu
 * @Program Spring_5
 * @create 2022-01-03-18:13
 */
// <bean id="userDao" class="dataTest.dao.impl.UserDaoImpl"/>
@Component("userDao")
//@Repository("userDao")
//@Scope("singleton")
// @Scope("prototype") 使用@Scope标注Bean的范围
public class UserDaoImpl implements UserDao {

    public void save() {
        System.out.println("save running···");
    }
}
