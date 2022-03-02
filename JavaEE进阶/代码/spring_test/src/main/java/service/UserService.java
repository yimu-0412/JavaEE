package service;

import domain.User;

import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-02-0:12
 */
public interface UserService {

    public List<User> list();

    public void save(User user, Long[] roleIds);

    void del(Long userId);
}
