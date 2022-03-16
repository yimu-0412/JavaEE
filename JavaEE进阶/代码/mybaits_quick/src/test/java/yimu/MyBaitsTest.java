package yimu;

import com.mysql.cj.x.protobuf.MysqlxCursor;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-15-22:57
 */
public class MyBaitsTest {
    @Test
    public void test() throws IOException {

        /*User user = new User();
        user.setId(1);*/
        // user.setUsername("lisi");

        // 1.获取核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 2. 获取 sqlSession 工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 3. 获得 sqlSession 会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. 执行操作 参数：namespace + id
        List<User> userList = sqlSession.selectList("userMapper.findAll",1);
        // 5. 打印结果
        System.out.println(userList);
        // 6. 释放资源
        sqlSession.close();
    }


    @Test
    // 2. 插入操作
    public void test2() throws IOException {
        // 模拟 User 对象创建
        User user = new User();
        user.setId(4);
        user.setUsername("lisi");
        user.setPassword("abcd");

        // 1. 获取核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 2. 获取 sqlSession 工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 3. 获取 sqlSession 会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. 执行操作
        sqlSession.insert("userMapper.save",user);
        // 5. 事务提交
        sqlSession.commit();
        // 6. 释放资源
        sqlSession.close();
    }

    @Test
    // 3. 修改操作
    public void test3() throws IOException {
        User user = new User();
        user.setId(2);
        user.setUsername("tom");
        user.setPassword("abc");

        // 1. 加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 2. 获取 sqlSession 工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 3. 获取 sqlSession 会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. 执行操作
        sqlSession.update("userMapper.update",user);
        // 5. 事务提交
        sqlSession.commit();
        // 6. 释放资源
        sqlSession.close();
    }

    @Test
    // 4. 删除操作
    public void test4() throws IOException {
        User user = new User();
        user.setUsername("lisi");

        // 1. 加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 2. 获取 sqlSession 工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 3. 获取 sqlSession 会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. 执行操作
        sqlSession.delete("userMapper.delete",user);
        // 5. 事务提交
        sqlSession.commit();
        // 6. 释放资源
        sqlSession.close();
    }
}
