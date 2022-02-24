package yimu.factory;

import yimu.dao.UserDao;
import yimu.dao.impl.UserDaoImpl;

/**
 * @author wangyimu
 * @Program Spring
 * @create 2022-02-19-11:52
 */
public class DynamicFactory {
    public UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
