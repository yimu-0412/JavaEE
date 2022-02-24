package yimu.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import yimu.dao.UserDao;
import org.springframework.stereotype.Component;


/**
 * @author wangyimu
 * @Program Spring
 * @create 2022-02-19-18:27
 */

// <bean id="userDao" class="yimu.dao.impl.UserDaoImpl"></bean>
// @Component("userDao")
@Repository("userDao")
@Scope("prototype")
public class UserDaoImpl implements UserDao {

    public void save() {
        System.out.println("save running···");
    }
}
