package dao;

import domain.User;

import java.io.IOException;
import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-17-21:24
 */
public interface UserMapper {
    public List<User> findAll() throws IOException;

    public User findById(int id);

    public void insert(User user);

    public void update(User user);

    public void delete(int id);


}
