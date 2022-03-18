## 一、MyBatis 的简介

### 1、原始的jdbc操作

1. 插入数据

    ```
    // 模拟 User 对象创建
    User user = new User();
    user.setId(4);
    user.setUsername("lisi");
    user.setPassword("abcd");

    // 注册驱动
    Class.forName("com.mysql.cj.jdbc.Driver");
    // 获得连接
    Connection connection = 
            DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "0412");
    // 获得 Statement
    PreparedStatement statement = 
            connection.prepareStatement("insert into user(id,username,password) values(?,?,?)");
    // 设置占位符
    statement.setInt(1,user.getId());
    statement.setString(1,user.getUsername());
    statement.setString(3,user.getPassword());
        
    // 执行更新操作
    statement.executeUpdate();
    // 释放资源
    statement.close();
    connection.close();    
    ```
2. 查询操作

    ```
    // 注册驱动
    Class.forName("com.mysql.cj.jdbc.Driver");
    // 获得连接
    Connection connection = 
            DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "0412");
    // 获得 Statement
    PreparedStatement statement =
            connection.prepareStatement("select id,username,passwoord from user");
    // 执行查询
    ResultSet resultSet = statement.executeQuery();
    // 遍历结果集
    while(resultSet.next()){
        // 封装实体
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        // user 实体封装完毕
        System.out.println(user);
    }
    ```
### 2、原始jdbc操作存在的问题
1. 存在问题

   1. 数据库连接创建、释放频繁造成系统资源浪费从而系统性能
   2. sql 语句在代码中硬编码，造成代码不易维护，实际应用sql变化的可能较大，sql变动需要改变java代码。
   3. 查询操作时，需要手动将结果集中的数据手动封装到实体中。插入操作时，需要手动将实体的数据设置到sql语句的占位符位置

2. 解决方案

    1. 使用数据库连接池初始化连接资源
    2. 将sql语句抽取到xml配置文件中
    3. 使用反射、内省等底层技术，自动将实体与表进行属性和字段的自动映射

### 3、什么是 MyBatis

* mybatis是一个优秀的基于java的持久层框架，它内部封装了jdbc，使开发者只需要关注sql语句本身，而不需要花费精力去处理加载驱动、创建连接、创建statement等繁杂的过程。
* mybatis通过xml或注解的方式将要执行的各种statement配置起来，并通过java对象和statement中sql的动态参数进行映射生成最终执行的sql语句。 
* 最后mybatis框架执行sql并将结果映射为java对象并返回。采用ORM思想解决了实体和数据库映射的问题，对jdbc进行了封装，屏蔽了jdbcapi底层访问细节，使我们不用与jdbcapi打交道，就可以完成对数据库的持久化操作。

## 二、MyBatis 的快速入门

### 1、MyBatis 开发步骤

1. 添加 MyBatis 的坐标
2. 创建 user 数据表
3. 编写 User 实体类
4. 编写映射文件 UserMapper.xml
5. 编写核心文件 SqlMapConfig.xml
6. 编写测试类

### 2、环境搭建
1. 导入MyBatis的坐标和其他相关坐标

    ```
    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.28</version>
    </dependency>


    <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.6</version>
    </dependency>

    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>

    <!-- https://mvnrepository.com/artifact/log4j/log4j -->
    <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.17</version>
    </dependency>
    ```
2. 创建 user 数据表

    ![User 数据表](https://raw.githubusercontent.com/yimu-0412/image/master/image/User%20%E6%95%B0%E6%8D%AE%E8%A1%A8.png)

3. 编写 User 实体

    ```
    public class User {
        private int id;
        private String username;
        private String password;
        //省略get个set方法
    }
    ```
4. 编写 UserMapper 映射文件

    ```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

    <mapper namespace="userMapper">
        <!--查询操作-->
        <select id="findAll" resultType="user" parameterType="int">
            select * from user where id = #{id}
        </select>
    </mapper>
    ```
5. 编写 MyBatis 核心文件

    ```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>

        <properties resource="jdbc.properties"></properties>

        <typeAliases>
            <typeAlias type="domain.User" alias="user"></typeAlias>
        </typeAliases>
        <!--数据源环境-->
        <environments default="developement">
            <environment id="developement">
                <transactionManager type="JDBC"></transactionManager>
                <dataSource type="POOLED">
                    <property name="driver" value="${jdbc.driver}"/>
                    <property name="url" value="${jdbc.url}"/>
                    <property name="username" value="${jdbc.username}"/>
                    <property name="password" value="${jdbc.password}"/>
                </dataSource>
            </environment>
        </environments>

        <!--加载映射文件-->
        <mappers>
            <mapper resource="mapper\UserMapper.xml"/>
        </mappers>
        
    </configuration>
    ```
6. 编写测试代码

    ```
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
    ```
 
## 三、MyBatis 的映射文件概述

&emsp;&emsp;![MyBatis 的映射文件概述](https://raw.githubusercontent.com/yimu-0412/image/master/image/MyBatis%20%E7%9A%84%E6%98%A0%E5%B0%84%E6%96%87%E4%BB%B6%E6%A6%82%E8%BF%B0.png)

## 四、MyBatis 的增删改查操作

### 1、MyBatis 的插入数据操作

1. 编写UserMapper映射文件

    ```
    <!--插入操作-->
    <mapper namespace="userMapper">
        <insert id="save" parameterType="user">
            insert into user values (#{id},#{username},#{password})
        </insert>
    </mapper>
    ```
2. 编写插入实体 User 的代码

    ```
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
    ```
3. 插入操作注意问题

   * 插入语句使用 insert 标签
   * 在映射文件中使用 parameterType 属指定要插入的数据类型
   * Sql 语句中使用``#{实体属性名}``方式引用实体中的属性值
   * 插入操作使用的 API 是 sqlSession(“命名空间.id”，“实体对象”)
   * 插入操作设计数据库数据变化，所以使用 sqlSession 对象显示的提交事务,即 sqlSession.commit()
  
### 2、MyBatis 的修改数据操作

1. 编写 UserMappper 映射文件

    ```
    <mapper namespace="userMapper">
        <!--修改操作-->
        <update id="update" parameterType="domain.User">
            update user set username=#{username},password=#{password} where id=#{id}
        </update>
    </mapper>
    ```
2. 编写修改实体 User 的代码

    ```
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
    ```
3. 修改操作注意问题

   * 修改语句使用 update 标签。
   * 修改操作使用的 API 是 sqlSession.update（"命名空间.id",实体对象）。

### 3、MyBatis 的删除数据操作
1. 编写 UserMapper 的映射文件

    ```
    <mapper namespace="userMapper">
        <!--修改操作-->
        <delete id="delete" parameterType="java.lang.Integer">
            delete from user where id = #{id}
        </update>
    </mapper>
    ```
2. 编写删除代码的数据

    ```
     @Test
    // 4. 删除操作
    public void test4() throws IOException {
        User user = new User();
        user.setId(1);

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
    ```
3. 删除操作注意问题

    * 删除语句使用 delete 标签。
    * Sql 语句中使用``#{任意字符串}``方式引用传递的单个参数。
    * 删除操作使用的 API 是 sqlSession.delete（“命名空间.id”，Object）。

### 4、知识小结

&emsp;&emsp;增删改查映射配置和 API:

```
    <mapper namespace="userMapper">
        <!--查询操作-->
        <select id="findAll" resultType="user" parameterType="int">
            select * from user where id = #{id}
        </select>

        <!--插入操作-->
        <insert id="save" parameterType="user">
            insert into user values (#{id},#{username},#{password})
        </insert>

        <!--修改操作-->
        <update id="update" parameterType="domain.User">
            update user set username=#{username},password=#{password} where id=#{id}
        </update>

        <!--删除操作-->
        <delete id="delete" parameterType="java.lang.Integer">
            delete from user where id = #{id}
        </delete>
    </mapper>
``` 
## 五、MyBatis 的核心配置文件概述
### 1、MyBatis 核心配置文件层级关系

* Configuration 配置
  * properties 属性
  * settings 设置
  * typeAliases 类型别名
  * typeHandlers 类型处理器
  * objectFactroy 对象工厂
  * plugins 插件
  * environments 环境
    * environment 环境变量
      * transactionManager 事务管理器
      * dataSource 数据源
    * databaseIdProvider 数据库厂商标识
    * mappers 映射器

### 2、MyBatis常用配置解析
1. environments 标签

    数据库环境的配置，**支持多环境配置**

    ![environments 标签](https://raw.githubusercontent.com/yimu-0412/image/master/image/environments%20%E6%A0%87%E7%AD%BE.png)

    其中，事务管理器（transactionManager）类型有两种：

    * JDBC:这个配置就是直接使用了 JDBC 的提交和回滚设置，它依赖于从数据源得到的连接来管理事务的作用域
    * MANAGER：这个配置几乎不做什么。从来不提交或者回滚一个连接，而是让容器来管理事务的整个生命周期（比如JEE应用服务器上下文）。默认情况下会关闭连接，然而一些容器并不希望这样，因此需要将 closeConnection 属性设置为 false 来组织它默认的关闭行为。

    其中，数据源（dataSource）类型有三种：
    * UNPOOLED：这个数据源的实现只是每次被请求时打开和关闭连接。
    * POOLED：这种数据源的实现利用“池”的概念将JDBC连接对象组织起来。
    * JNDI：这个数据源的实现是为了能在如 EJB 或应用服务器这类容器中使用，容器可以集中或在外部配置数据源，然后放置一个 JDNI 上下文的引用。

2. mapper 标签
   
    该标签的作用是加载映射的，加载方式有如下几种：
    * 使用相对类路径的资源引用，例如：``<mapper resource="org/mybatis/builder/AuthorMapper.xml"/>``
    * 使用完全限定资源定位符，例如：``<mapper url="file:///var/mappers/AuthorMapper.xml"/>``
    * 使用映射器接口类实现类的完全限定类名，例如：``<mapper class="org.mybatis.builder.AuthorMapper"/>``
    * 将包内的映射器接口实现全部注册为映射器，例如：``<package name="org.mybatis.builder"/>``

3. Properties 标签

    实际开发中，习惯将数据源的配置信息单独抽取成一个 properties 文件，该标签可以加载额外配置的 properties 文件

    ![Properties 标签](https://raw.githubusercontent.com/yimu-0412/image/master/image/Properties%20%E6%A0%87%E7%AD%BE.png)

4. typeAliases 标签

    类型别名是为 Java 类型设置一个简短的名字。原来的类型名称配置如下：

    ```
    <!--查询操作-->
    <select id="findAll" resultType="domain.User"> 
        select * from user
    </select>
    ```
    配置 typeAliases，为 ``domain.User`` 定义别名为 user。
    
    ```
     <typeAliases>
        <typeAlias type="domain.User" alias="user"></typeAlias>
    </typeAliases>
    ```

    ```
    <!--查询操作-->
    <select id="findAll" resultType="user"> 
        select * from user
    </select>
    ```
    上面是自定义的别名，mybatis 框架已经默认设置好一些常用的类型的别名

    ![typeAliases 标签](https://raw.githubusercontent.com/yimu-0412/image/master/image/typeAliases%20%E6%A0%87%E7%AD%BE.png)

### 3、知识小结

&emsp;&emsp;**核心配置文件常用的配置**：

1. properties标签：该标签可以加载外部的properties文件
   ```
    <properties resource="jdbc.properties"></properties>
   ```
2. typeAliases标签：设置类型别名

    ```
    <typeAlias type="domain.User" alias="user"></typeAlias>
    ```
3. mappers标签：加载映射配置

    ```
    <mapper resource="mapper\UserMapper.xml"/>
    ```
## 六、MyBatis 的相应 API

1. SqlSession工厂构建器SqlSessionFactoryBuilder

   **常用API**：SqlSessionFactorybuild(InputStreaminputStream)
   通过加载 mybatis 的核心配置文件的输入流的形式构建一个 ``SqlSessionFactroy
   ``对象

   ```
   String resources = "sqlMapConfig.xml";
   InputStream inputStream = Resources.getResourceAsStream(resources);
   SqlSessionFactoryBuilder builder = newSqlSessionFactoryBuilder();
   SqlSessionFactory factory = builder.build(inputStream);
   ```
   其中，``Resources`` 工具类，这个类在 ``org.apache.ibatis.io`` 包中。``Resources`` 类帮助你从类路径下、文件系统或一个 ``web URL`` 中加载资源文件。

2. SqlSession 工厂对象 SqlSessionFactory

    SqlSessionFactory 有多个个方法创建 SqlSession 实例。常用的有如下两个：

    ![SqlSessionFactory 常用的方法](https://raw.githubusercontent.com/yimu-0412/image/master/image/SqlSessionFactory%20%E5%B8%B8%E7%94%A8%E7%9A%84%E6%96%B9%E6%B3%95.png)

3. SqlSession 会话对象 SqlSession 会话对象

    SqlSession实例在MyBatis中是非常强大的一个类。在这里你会看到所有执行语句、提交或回滚事务和获取映射器实例的方法。**执行语句**的方法主要有：

    ```
    <T>T selectOne(String statement, Object parameter)

    <E>List<E>selectList(String statement, Object parameter)

    intinsert(String statement, Object parameter)

    intupdate(String statement, Object parameter)

    intdelete(String statement, Object parameter)
    ```
    **操作事务**的方法主要有：
    ```
    void commit()
    void rollback()
    ```