package yimu.factory;

import yimu.dao.UserDao;
import yimu.dao.impl.UserDaoImpl;

/**
 * @author wangyimu
 * @Program Spring
 * @create 2022-02-19-11:48
 */
public class StaticFactory {
    public static UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
