import domain.User;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-23-11:12
 */
public class MyBatiTest {

    private UserMapper mapper;

    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        mapper = sqlSession.getMapper(UserMapper.class);
    }


    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("huahua");
        user.setPassword("hfh");
        user.setBirthday(new Date());
        mapper.save(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(8);
        user.setUsername("柯基");
        user.setPassword("kkk");

        mapper.update(user);
    }

    @Test
    public void testDelete(){
        mapper.delete(1);
    }

    @Test
    public void testfindAll(){
        List<User> userList = mapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testfindById(){
        User user = mapper.findById(7);
        System.out.println(user);
    }
}
