package dao;

import domain.User;

import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-02-0:14
 */
public interface UserDao {

    public List<User> findAll();

    public Long save(User user);

    public void saveUserRoleRel(Long id, Long[] roleIds);

    void delUserRel(Long userId);

    void del(Long userId);
}
