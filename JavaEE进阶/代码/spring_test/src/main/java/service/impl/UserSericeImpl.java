package service.impl;

import dao.RoleDao;
import dao.UserDao;
import domain.Role;
import domain.User;
import service.UserService;

import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-02-0:13
 */
public class UserSericeImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<User> list() {
        List<User> userList = userDao.findAll();
        // 1.封装userList中的roles数据
        for (User user : userList) {
            // 获取 user 的 id
            Long id = user.getId();
            // 将 id 作为参数，查询当前 userId 对应的 Role 集合
            List<Role> roles = roleDao.findRoleByUserId(id);
            user.setRoles(roles);
        }
        return userList;
    }

    @Override
    public void save(User user, Long[] roleIds) {
        // 1.向sys_user表中存储数据
        Long userId = userDao.save(user);
        // 2.向sys_user_role表中存储多条数据
        userDao.saveUserRoleRel(userId,roleIds);
    }

    @Override
    public void del(Long userId) {
        // 1、删除 sys_user_role 表
        userDao.delUserRel(userId);
        // 2、删除 sys_user 表
        userDao.del(userId);
    }
}
