package mapper;

import domain.User;

import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-19-15:21
 */
public interface UserMapper {
    public List<User> findByCondition(User user);

    public List<User> findByIds(List<Integer> ids);
}
