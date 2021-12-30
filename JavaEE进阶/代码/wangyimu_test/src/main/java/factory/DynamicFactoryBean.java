package factory;

import Dao.UserDao;
import Dao.impl.UserDaoImpl;

/**
 * @author wangyimu
 * @Program wangyimu_test
 * @create 2021-12-30-21:09
 */
public class DynamicFactoryBean {
    public DynamicFactoryBean() {
    }

    public UserDao createUserDao(){
        return new UserDaoImpl();
    }
}
