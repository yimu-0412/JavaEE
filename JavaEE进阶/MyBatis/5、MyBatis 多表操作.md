## 一、一对一查询

1. **一对一查询的模型**

    用户表和订单表的关系为，一个用户有多个订单，一个订单只能属于一个用户。
    一对一查询的需求：查询一个订单，与此同时查询出该订单所属的用户。

    ![一对一查询的模型](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E4%B8%80%E5%AF%B9%E4%B8%80%E6%9F%A5%E8%AF%A2%E7%9A%84%E6%A8%A1%E5%9E%8B.png)

2. **一对一查询的语句**

    对应的sql查询语句：``select * ,o.uid oid,u.id ud from orders o,user u where o.uid = u.id;``

    查询的结果如下：

    ![一对一查询结果](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E4%B8%80%E5%AF%B9%E4%B8%80%E6%9F%A5%E8%AF%A2%E7%BB%93%E6%9E%9C.png)

3. **创建 Order 和 User 实体**

   ```
   public class Order {
        private int id;
        private Date ordertime;
        private double total;
        private User user; // 当前订单属于那个用户
        
        ···
        get 和 set 方法
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
    
        ···
        get 和 set 方法
        toString 方法
        ···
    }
   ``` 
4. **创建 OrderMapper 接口**

    ```
    public interface OrderMapper {
        public List<Order> findAll();
    }
    ```
5. **配置 ``OrderMapper.xml``**

    ```
    <mapper namespace="mapper.OrderMapper">

    <resultMap id="orderMap" type="order">
        <!--手动指定字段与实体之间的映射关系
            column:数据表的字段名称
            property:实体的属性名称
        -->

        <id column="oid" property="id"></id>
        <result column="ordertime" property="ordertime"></result>
        <result column="total" property="total"></result>

        <!--<result column="ud" property="user.id"></result>
        <result column="username" property="user.username"></result>
        <result column="password" property="user.password"></result>
        <result column="birthday" property="user.birthday"></result>-->

        <!--
            property:当前实体（order）中的属性名称（private User user）
            javaType：当前实体（order）中的属性的类型（User）
        -->
        <association property="user" javaType="user">
            <id column="ud" property="id"></id>
            <result column="username" property="username"></result>
            <result column="password" property="password"></result>
            <result column="birthday" property="birthday"></result>
        </association>
    </resultMap>

    <select id="findAll" resultMap="orderMap">
        select *,o.id oid,u.id ud from orders o,user u where o.uid = u.id ;
    </select>
    ```
6. **测试结果**

    ```
    @Test
    // 一对一数据表查询
    public void test1() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> orderList = mapper.findAll();
        for (Order order : orderList) {
            System.out.println(order);
        }

        sqlSession.close();
    }
    ```

    ![一对一测试代码结果显示](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E4%B8%80%E5%AF%B9%E4%B8%80%E6%B5%8B%E8%AF%95%E4%BB%A3%E7%A0%81%E7%BB%93%E6%9E%9C%E6%98%BE%E7%A4%BA.png)

## 二、一对多查询

1. **一对多查询模型**

    用户表和订单表的关系为，一个用户有多个订单，一个订单只能属于一个用户。
     
    一对多查询的需求：查询一个用户，与此同时查询出该用户所具有的订单。

    ![一对一查询的模型](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E4%B8%80%E5%AF%B9%E4%B8%80%E6%9F%A5%E8%AF%A2%E7%9A%84%E6%A8%A1%E5%9E%8B.png)

2. **一对多的查询语句**

    对用的sql语句：``select *,u.id ud,o.id oid from user u,orders o where u.id = o.uid``

    ![一对多查询结果](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E4%B8%80%E5%AF%B9%E5%A4%9A%E6%9F%A5%E8%AF%A2%E7%BB%93%E6%9E%9C.png)

3. **修改 User 实体**

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
4. **创建 UserMapper 接口**

    ```
    public interface UserMapper {
        public List<User> findAll();
    }
    ```
5. **配置 ``UserMapper.xml`**`

    ```
    <mapper namespace="mapper.UserMapper">

        <resultMap id="userMap" type="user">
            <id column="ud" property="id"></id>
            <result column="username" property="username"></result>
            <result column="password" property="password"></result>
            <result column="birthday" property="birthday"></result>

            <!--配置集合信息
                property:集合名称
                ofType:当前集合中的数据类型
            -->
            <collection property="orderList" ofType="order">
                <!--封装 order 的数据-->
                <id column="oid" property="id"></id>
                <result column="ordertime" property="ordertime"></result>
                <result column="total" property="total"></result>
            </collection>
        </resultMap>

        <select id="findAll" resultMap="userMap">
            select *,u.id ud ,o.id oid from user u,orders o where u.id = o.uid;
        </select>

    </mapper>
    ```

6. **测试结果**

    ```
    public class MyBatisTest2 {
        @Test
        // 一对多数据表查询
        public void test2() throws IOException {

            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);

            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.findAll();
            for (User user : userList) {
                System.out.println(user);
            }

            sqlSession.close();
        }
    }
    ```
    
    ![一对多代码测试结果](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E4%B8%80%E5%AF%B9%E5%A4%9A%E4%BB%A3%E7%A0%81%E6%B5%8B%E8%AF%95%E7%BB%93%E6%9E%9C.png)

## 三、多对多查询

1. **多对多查询的模型**

    用户表和角色表的关系为，一个用户有多个角色，一个角色被多个用户使用。
    多对多查询的需求：查询用户同时查询出该用户的所有角色。

    ![多对多的查询](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%A4%9A%E5%AF%B9%E5%A4%9A%E7%9A%84%E6%9F%A5%E8%AF%A2.png)


2. **多对多查询的语句**

    对应的sql语句：``select * from user u,sys_user_role ur,sys_role r where u.id = ur.userId and ur.roleId = r.id``

    查询结果如下：

    ![多对多查询结果](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%A4%9A%E5%AF%B9%E5%A4%9A%E6%9F%A5%E8%AF%A2%E7%BB%93%E6%9E%9C.png)

3. **创建 Role 实体，修改 User 实体**

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

4. **添加 UserMapper 接口方法**

    ```
    public interface UserMapper {
        public List<User> findAll();
        public List<User> findUserAndRoleAll();
    }
    ```
5. **配置 UserMapper.xml 接口**

    ```
    <mapper namespace="mapper.UserMapper">
        <resultMap id="userRoleMap" type="user">
            <!--user 的信息-->
            <id column="userId" property="id"></id>
            <result column="username" property="username"></result>
            <result column="password" property="password"></result>
            <result column="birthday" property="birthday"></result>

            <!--user内部的 userList信息-->
            <collection property="roleList" ofType="role">
                <id column="roleId" property="id"></id>
                <result column="roleName" property="roleName"></result>
                <result column="roleDesc" property="roleDesc"></result>
            </collection>
        </resultMap>

        <select id="findUserAndRoleAll" resultMap="userRoleMap">
            select * from user u,sys_user_role ur,sys_role r where u.id=ur.userId and ur.roleid=r.id
        </select>
    </mapper>
    ```
6. 测试代码

    ```
    @Test
    // 多对多数据表查询
    public void test3() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findUserAndRoleAll();
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }
    ```

    ![多对多代码测试结果显示](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%A4%9A%E5%AF%B9%E5%A4%9A%E4%BB%A3%E7%A0%81%E6%B5%8B%E8%AF%95%E7%BB%93%E6%9E%9C%E6%98%BE%E7%A4%BA.png)

## 四、知识小结

&emsp;MyBatis多表配置方式：

* 一对一配置：使用 ``<resultMap>`` 做配置
* 一对多配置：使用 ``<resultMap>+<collection>`` 做配置
* 多对多配置：使用 ``<resultMap>+<collection>`` 做配置
