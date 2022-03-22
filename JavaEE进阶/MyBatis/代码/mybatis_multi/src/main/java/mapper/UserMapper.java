package mapper;

import domain.User;

import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-22-11:19
 */
public interface UserMapper {
    public List<User> findAll();

    public List<User> findUserAndRoleAll();
}
