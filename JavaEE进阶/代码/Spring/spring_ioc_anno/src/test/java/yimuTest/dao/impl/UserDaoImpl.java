package yimuTest.dao.impl;

import org.springframework.stereotype.Repository;
import yimuTest.dao.UserDao;
import org.springframework.stereotype.Component;

/**
 * @author wangyimu
 * @Program Spring_5
 * @create 2022-01-02-17:14
 */

/*<bean id="userDao" class="yimuTest.dao.impl.UserDaoImpl"/>*/

/*@Component("userDao")*/
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    public void save() {
        System.out.println("save running···");
    }
}
