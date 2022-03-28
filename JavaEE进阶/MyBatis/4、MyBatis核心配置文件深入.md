## 一、typeHandlers 标签

&emsp;&emsp;无论是MyBatis在预处理语句（PreparedStatement）中设置一个参数时，还是从结果集中取出一个值时，都会用类型处理器将获取的值以合适的方式转换成Java 类型。下表描述了一些默认的类型处理器（截取部分）。

&emsp;&emsp;![mybatis类型处理器](https://raw.githubusercontent.com/yimu-0412/image/master/image/mybatis%E7%B1%BB%E5%9E%8B%E5%A4%84%E7%90%86%E5%99%A8.png)‘

&emsp;&emsp;可以重写类型处理器或者创建自定义的类型处理器来处理不支持的或者非标准的类型。**具体的做法为**：实现 ``org.apache.ibatis.type.TypeHandler`` 接口，或继承一个很便利的类：``org.apache.ibatis.type.BaseTypeHandler``,然后选择性的将它映射到一个 JDBC 类型。

&emsp;&emsp;例如需求：一个Java中的Date数据类型，将之存到数据库的时候存成一个1970年到至今的毫秒数，取出来转换为java的Date，即java的Date与数据库的varchar毫秒值之间转换。

### 1、开发步骤总结：

1. 定义转换类继承类 ``BaseTypeHandler``
2. 覆盖4个未实现的方法，其中 ``setNonNullParameter`` 为java程序设置数据到数据库的回调方法，getNullableResult 为查询时 mysql 的字符串类型转换为 java 的 Type 类型的方法。
3. 在MyBatis核心配置文件中进行注册。
4. 测试转换是否正常

### 2、代码实现

1. 定义转换继承类
2. 覆盖4个未实现的方法，其中 ``setNonNullParameter`` 为java程序设置数据到数据库的回调方法，getNullableResult 为查询时 mysql 的字符串类型转换为 java 的 Type 类型的方法。

    ```
    public class DataTypeHandler extends BaseTypeHandler<Date> {
        // 将 java 类型转换成数据库需要的类型
        public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
            long time = date.getTime();
            preparedStatement.setLong(i,time);
        }

        // 将数据库类型转换成 java 类型
        // String 参数代表要转换的名称
        // ResultSet 查询出的结果集
        public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
            // 获得结果集中需要的数据（long） 将其转换成date类型，并返回
            long aLong = resultSet.getLong(s);
            Date date = new Date(aLong);
            return date;
        }

        // 将数据库类型转换成 java 类型
        public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
            long aLong = resultSet.getLong(i);
            Date date = new Date(aLong);
            return date;
        }

        // 将数据库类型转换成 java 类型
        public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
            long aLong = callableStatement.getLong(i);
            Date date = new Date(aLong);
            return date;
        }
    }
    ```
3. 在MyBatis核心配置文件中进行注册。

    ```
    <!--自定义类型处理器-->
    <typeHandlers>
       <typeHandler handler="handler.DataTypeHandler"/>
    </typeHandlers>
    ```
4. 测试代码

    测试添加操作
    ```
    public void test1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // 执行操作
        // 创建User对象
        User user = new User();
        user.setId(1);
        user.setUsername("lucy");
        user.setPassword("qwe");
        user.setBirthday(new Date());

        // 执行保存操作
        mapper.save(user);
        sqlSession.commit();
        sqlSession.close();
    }
    ```

    数据库数据：

    ![类型转换测试](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E7%B1%BB%E5%9E%8B%E8%BD%AC%E6%8D%A2%E6%B5%8B%E8%AF%95.png)

    测试查询操作：

    ```
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // 执行保存操作
        User user = mapper.findById(1);
        System.out.println("user中的birthday:" + user.getBirthday());
        sqlSession.commit();
        sqlSession.close();
    }
    ```

    ![测试查询操作](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%B5%8B%E8%AF%95%E6%9F%A5%E8%AF%A2%E6%93%8D%E4%BD%9C.png)

## 二、plugins 标签

&emsp;&emsp;MyBatis可以使用第三方的插件来对功能进行扩展，分页助手PageHelper是将分页的复杂操作进行封装，使用简单的方式即可获得分页的相关数据。

### 1、开发步骤

1. 导入通用 PageHelper的坐标
2. 在 mybatis 核心配置文件中配置 PageHelper 插件
3. 测试分页数据获取

### 2、具体实现

1. 导入通用 PageHelper的坐标

    ```
    <!-- https://mvnrepository.com/artifact/com.githubpagehelper/pagehelper -->
    <dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper</artifactId>
        <version>5.2.0</version>
    </dependency>

    <dependency>
        <groupId>com.github.jsqlparser</groupId>
        <artifactId>jsqlparser</artifactId>
        <version>3.2</version>
    </dependency>
    ```
2. 在 mybatis 核心配置文件中配置 PageHelper 插件

    ```
    <!--配置分页助手插件-->
    <plugins>
        <plugin interceptor="com.github.pagehelper.PageInterceptor">
            <!--<property name="dialect" value="mysql"/>-->
        </plugin>
    </plugins>
    ```
3. 测试分页代码实现

    ```
    public void test3() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 设置分页的相关参数 ：当前页每页显示的条数
        PageHelper.startPage(2,2);

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }

        
        sqlSession.close();
    }
    ```

    测试分页代码：

    ![测试分页代码](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%B5%8B%E8%AF%95%E5%88%86%E9%A1%B5%E4%BB%A3%E7%A0%81.png)

    测试获得分页相关的其他参数

    ```
    // 获得与分页相关的参数
    PageInfo<User> pageInfo = new PageInfo<User>(userList);
    System.out.println("当前页：" + pageInfo.getPageNum());
    System.out.println("每一页显示条数：" + pageInfo.getPageSize());
    System.out.println("总条数：" +  pageInfo.getTotal());
    System.out.println("总页数：" + pageInfo.getPages());
    System.out.println("上一页：" + pageInfo.getPrePage());
    System.out.println("下一页：" + pageInfo.getNextPage());
    System.out.println("是否是第一页：" + pageInfo.isIsFirstPage());
    System.out.println("是否为最后一页：" + pageInfo.isIsLastPage());
    ```

    测试分页的其他方法：

    ![测试分页的其他方法](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%B5%8B%E8%AF%95%E5%88%86%E9%A1%B5%E7%9A%84%E5%85%B6%E4%BB%96%E6%96%B9%E6%B3%95.png)
    
## 三、知识小结

**MyBatis核心配置文件常用标签**：

1. ``properties`` 标签：该标签可以加载外部的properties文件
2. ``typeAliases`` 标签：设置类型别名
3. ``environments`` 标签：数据源环境配置标签
4. ``typeHandlers`` 标签：配置自定义类型处理器
5. ``plugins`` 标签：配置MyBatis的插件

