package dao;

import domain.Role;

import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-01-21:25
 */
public interface RoleDao {
    public List<Role> findAll();

    public void save(Role role);

    public List<Role> findRoleByUserId(Long id);

    void del(Long id);
}
