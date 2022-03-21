package mapper;


import domain.User;

import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-19-15:21
 */
public interface UserMapper {
    public void save(User user);

    public User findById(int id);

    public List<User> findAll();
}
