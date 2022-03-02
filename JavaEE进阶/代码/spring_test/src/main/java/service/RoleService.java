package service;

import domain.Role;

import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-01-21:10
 */
public interface RoleService {
    public List<Role> list();

    public String save(Role role);

    void del(Long id);
}
