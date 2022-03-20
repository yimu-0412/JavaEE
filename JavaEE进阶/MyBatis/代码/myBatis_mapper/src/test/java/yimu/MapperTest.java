package yimu;

import domain.User;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-19-15:27
 */
public class MapperTest {
    @Test
    public void test1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 模拟条件 user

        User condition = new User();
        condition.setId(1);
        // condition.setUsername("zhangsan");
        // condition.setPassword("1");
        List<User> userList = mapper.findByCondition(condition);
        System.out.println(userList);

        // 模拟 ids 的数据
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        // ids.add(4);

        List<User> users = mapper.findByIds(ids);
        System.out.println(users);
        sqlSession.close();
    }
}
