package service.impl;

import dao.RoleDao;
import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import service.RoleService;

import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-01-21:12
 */
public class RoleServicesImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> list() {
        List<Role> roleList = roleDao.findAll();
        return roleList;
    }

    @Override
    public String save(Role role) {
        roleDao.save(role);
        return null;
    }

    @Override
    public void del(Long roleId) {
        roleDao.del(roleId);
    }
}
