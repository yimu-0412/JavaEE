## 一、传统的开发方式

1. 编写 UserDao 接口

    ```    
    public interface UserMapper {
        public List<User> findAll() throws IOException;
    }
    ```
2. 编写 UserDaoImpl 实现

    ```
    public class UserDaoImpl implements UserDao{
        public List<User> findAll(){
            InputStream resourceStream = Resources.getResourcAsStream("SqlMapConfig.xml");
            SqlSessionFactroy sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceStream);
            SqlSession sqlSession = sqlSessionFactroy.openSession();
            List<User> userList = sqlSession.selectList("userMapper.findAll");
            sqlSession.close();
            return userList;
        }
    }
    ```
3. 测试传统方式

    ```
    @Test
    public void testTraditionalDao() throws IOException{
        UserDao userDao = new UserDaoImpl();
        List<User> userList = userDao.findAll();
        System.out.println(userList);
    }
    ```
## 二、代理开发方式

1. 代理开发方式介绍

    **采用 MyBatis 的代理开发方式实现 DAO 层的开发,这种方式是进入企业的主流**。
    
    Mapper 接口开发方法只需要程序员编写 Mapper 接口（相当于Dao接口），由 MyBatis 框架根据接口定义创建接口的动态代理对象，代理对象的方法体同Dao接口实现类方法。
    
    Mapper 接口开发需要遵循以下规范：

    1. Mapper.xml 文件中的 namespace 与 mapper 接口的全限定名相同。
    2. Mapper 接口方法名和 Mapper.xml 中定义的每个 statement 的 id 相同。
    3. Mapper 接口方法的输入参数类型和 Mapper.xml 中定义的每个sql的 parameterType 类型相同。
    4. Mapper接口方法的输出参数类型和 Mapper.xml中定义的每个sql的 resultType 的类型相同。

2. 编写 UserMapper 接口

    ![UserMapper接口](https://raw.githubusercontent.com/yimu-0412/image/master/image/UserMapper%E6%8E%A5%E5%8F%A3.png)

3. 测试代理方式

    ```
    @Test
    public void testProxyDao() throws IOException{
        InputStream resourceAsStream= Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession= sqlSessionFactory.openSession();
        //获得MyBatis框架生成的UserMapper接口的实现类
        UserMapper userMapper= sqlSession.getMapper(UserMapper.class);
        User user= userMapper.findById(1);
        System.out.println(user);
        sqlSession.close();}
    ```
## 3、知识小结

   &emsp;&emsp;**MyBatis的Dao层实现的两种方式**： 

   * 手动对 Dao 进行实现：传统开发模式
   * 代理方式对 Dao 进行实现

     ```
     UserMapper userMapper= sqlSession.getMapper(UserMapper.class);
     ```

&emsp;&emsp;![UserMapper接口](https://raw.githubusercontent.com/yimu-0412/image/master/image/UserMapper%E6%8E%A5%E5%8F%A3.png)
