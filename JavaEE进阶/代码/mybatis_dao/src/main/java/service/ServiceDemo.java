package service;

import dao.UserMapper;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-17-21:38
 */
public class ServiceDemo {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

       /* // 查询所有
        List<User> userList = mapper.findAll();
        System.out.println(userList);

        System.out.println();
        // 根据 id查询
        User user1 = mapper.findById(1);
        System.out.println(user1);*/

        // 添加数据
        User user = new User();
        /*user.setId(5);
        user.setUsername("yimu");
        user.setPassword("yimu");
        mapper.insert(user);
        sqlSession.commit();
        System.out.println();*/

        /*// 更新数据
        user.setId(5);
        user.setUsername("yimu");
        user.setPassword("1234");
        mapper.update(user);*/

        // 删除数据
        mapper.delete(5);

    }
}
