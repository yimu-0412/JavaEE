## 一、Mybatis 的常用注解

&emsp;&emsp;MyBatis 可以使用注解方式开发，这样就可以减少编写Mapper映射文件。常用的CRUD注解学习：

* @Ins1ert：实现新增
* @Update：实现更新
* @Delete：实现删除
* @Select：实现查询
* @Result：实现结果集封装
* @Results：可以和@Result一起使用，封装多个结果集
* @One：实现一对一结果集封装
* @Many：实现一对多结果集封装

## 二、MyBatis 的增删改查

1. User 表的增删改查操作测试代码

    ```
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
    ```
2. 修改 MyBatis 核心配置文件，使用注解替代的映射文件，所以只需要加载使用了注解的Mapper接口即可。

    ```
    <mappers><!--扫描使用注解的类-->
        <mapper class="mapper.UserMapper"></mapper>
    </mappers>
    ```
    或者指定扫描映射关系的接口所在的包也可以。

    ```
    <!--加载映射关系 TODO-->
    <mappers>
        <!--指定接口所在的包-->
        <package name="mapper"/>
    </mappers>
    ```
3. 修改 UserMapper 接口，在方法上添加注解

    ```
    public interface UserMapper {

        @Insert("insert into user values (#{id},#{username},#{password},#{birthday})")
        public void save(User user);

        @Update("update user set username=#{username},password=#{password} where id=#{id}")
        public void update(User user);

        @Delete("delete from user where id = #{id}")
        public void delete(int id);

        @Select("select * from user where id = #{id}")
        public User findById(int id);

        @Select("select * from user")
        public List<User> findAll();
    }
    ```

## 三、MyBatis 的注解实现复杂的映射开发

&emsp;&emsp;实现复杂映射关系之前可以在映射文件中通过配置 ``<resultMap>`` 来实现，使用注解开发后，可以使用 @Results 注解，@Result 注解，@One 注解，@Many 注解组合完成复杂关系的配置。

&emsp;&emsp;![MyBatis 注解1](https://raw.githubusercontent.com/yimu-0412/image/master/image/MyBatis%20%E6%B3%A8%E8%A7%A31.png)

&emsp;&emsp;![MyBatis 注解2](https://raw.githubusercontent.com/yimu-0412/image/master/image/MyBatis%20%E6%B3%A8%E8%A7%A32.png)

## 四、一对一查询

1. 一对一查询的模型

    用户表和订单表的关系为，一个用户有多个订单，一个订单只从属于一个用户。

    一对一查询的需求：查询一个订单，与此同时查询出该订单所属的用户。

2. 一对一查询的语句

    对应的sql语句：

    ```
    select * from orders;`
    select * from user where id = 查询出订单的``uid``；
    ```
    查询的结果如下：

    ![一对一查询结果1](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E4%B8%80%E5%AF%B9%E4%B8%80%E6%9F%A5%E8%AF%A2%E7%BB%93%E6%9E%9C1.png)

3. 创建Order和User实体

    ```
    public class Order {
        private int id;
        private Date ordertime;
        private double total;
        
        private User user; // 当前订单属于那个用户
    }
    ```

    ```
    public class User {
        private int id;
        private String username;
        private String password;
    }
    ```
4. 创建OrderMapper接口

    ```
    public interface OrderMapper {
        public List<Order> findAll();
    }
    ```
5. 使用注解配置 Mapper 接口

    ```
    public interface OrderMapper {

        @Select("select * from orders")
        @Results({
                @Result(column = "id",property = "id"),
                @Result(column = "ordertime",property = "ordertime"),
                @Result(column = "total",property = "total"),
                @Result(column = "uid",property = "user",
                        javaType = User.class,
                        one = @One(select = "mapper.UserMapper.findById"))
        })
        public List<Order> findAll();
    }
    ```

    ```
    @Select("select * from user where id = #{id}")
    public User findById(int id);
    ```
6. 测试结果

    ```
    public class MyBatiTest2 {
        private OrderMapper mapper;

        @Before
        public void before() throws IOException {
            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            mapper = sqlSession.getMapper(OrderMapper.class);
        }

        @Test
        public void testfindAll(){
            List<Order> orderList = mapper.findAll();
            for (Order order : orderList) {
                System.out.println(order);
            }
        }
    }
    ```

    ![一对一测试代码结果显示1](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E4%B8%80%E5%AF%B9%E4%B8%80%E6%B5%8B%E8%AF%95%E4%BB%A3%E7%A0%81%E7%BB%93%E6%9E%9C%E6%98%BE%E7%A4%BA1.png)

## 五、一对多查询的模型

1. 一对多查询的模型

    用户表和订单表的关系为，一个用户有多个订单，一个订单只从属于一个用户。

    一对多查询的需求：查询一个用户，与此同时查询出该用户具有的订单。

2. 一对多查询的语句

    对应的sql语句：
    ```
    select * from user;
    select * from orders where uid=查询出用户的id;
    ```
    查询结果如下：

    ![一对多查询结果](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E4%B8%80%E5%AF%B9%E5%A4%9A%E6%9F%A5%E8%AF%A2%E7%BB%93%E6%9E%9C.png)

3. 修改User实体

    ```
    public class User {
        private int id;
        private String username;
        private String password;
        private Date birthday;

        // 描当前用户存在那些订单
        private List<Order> orderList;

        ···
        set、get方法
        toString 方法
        ···
    }
    ```
4. 创建UserMapper接口

    ```
    public interface UserMapper {
        public List<User> findUserAndRoleAll();
    }
    ```
5. 使用注解配置Mapper

    ```
    @Select("select * from user")
    @Results({
            @Result(id = true,column="id",property ="id") ,
            @Result(column ="username",property = "username"),
            @Result(column = "password",property = "password"),

            @Result(
                    property = "orderList",
                    column = "id",
                    javaType = List.class,
                    many = @Many(select="mapper.OrderMapper.findBByUid")
            )
    })
    public List<User> findUserAndOrderAll();
    ```

    ```
    @Select("select * from orders where uid = #{uid}")
    public List<Order> findBByUid(int uid);
    ```
6. 测试结果

    ```
    public class MyBatiTest3 {
        private UserMapper mapper;

        @Before
        public void before() throws IOException {
            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            mapper = sqlSession.getMapper(UserMapper.class);
        }

        @Test
        public void testfindUserAndOrder(){
            List<User> userList = mapper.findUserAndOrderAll();
            for (User user : userList) {
                System.out.println(user);
            }
        }
    }
    ```

    ![一对多代码测试结果1](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E4%B8%80%E5%AF%B9%E5%A4%9A%E4%BB%A3%E7%A0%81%E6%B5%8B%E8%AF%95%E7%BB%93%E6%9E%9C1.png)

## 六、多对多查询的模型

1. 多对多查询的模型

    用户表和角色表的关系为，一个用户有多个角色，一个角色被多个用户使用。

    多对多查询的需求：查询用户同时查询出该用户的所有角色。

    ![多对多的查询](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%A4%9A%E5%AF%B9%E5%A4%9A%E7%9A%84%E6%9F%A5%E8%AF%A2.png)

2. 多对多查询的语句

    对应的sql语句：

    ```
    select * from user;
    select * from role r,user_roleurwhere r.id=ur.role_idand ur.user_id=用户的id
    ```
3. 创建Role实体，修改User实体

    ```
    public class Role {
        private int id;
        private String roleName;
        private String roleDesc;

        ···
        get和set方法
        toString 方法
        ···
    }
    ```

    ```
    public class User {
        private int id;
        private String username;
        private String password;
        private Date birthday;

        // 描述的是当前用户具备那些角色
        private List<Role> roleList;

        ···
        get和set方法
        toString 方法
        ···
    }
    ```
4. 添加UserMapper接口方法

    ```
    public interface UserMapper {
        public List<User> findUserAndRoleAll();
    }
    ```
5. 使用注解配置 Mapper

    ```
    public interface UserMapper {
        @Select("select * from user")
        @Results({
                @Result(column = "id",property = "id"),
                @Result(column ="username",property = "username"),
                @Result(column = "password",property = "password"),
                @Result(
                        property = "roleList",
                        column = "id",
                        javaType = List.class,
                        many = @Many(select="mapper.RoleMapper.findByUid")
                )
        })
        public List<User> findUserAndRoleAll();
    }
    ```

    ```
    public interface RoleMapper {
        @Select("select * from sys_user_role ur,sys_role r where ur.roleId = r.id and ur.userId = #{id}")
        public List<Role> findByUid(int id);
    }
    ```
6. 测试结果

    ```
    public class MyBatiTest4 {
        private UserMapper mapper;

        @Before
        public void before() throws IOException {
            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            mapper = sqlSession.getMapper(UserMapper.class);
        }

        @Test
        public void testfindUserAndRole(){
            List<User> userList = mapper.findUserAndRoleAll();
            for (User user : userList) {
                System.out.println(user);
            }
        }
    }
    ```
    
    ![多对多代码测试结果显示1](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%A4%9A%E5%AF%B9%E5%A4%9A%E4%BB%A3%E7%A0%81%E6%B5%8B%E8%AF%95%E7%BB%93%E6%9E%9C%E6%98%BE%E7%A4%BA1.png)