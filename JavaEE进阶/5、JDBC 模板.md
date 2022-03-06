## 1、JdbcTemplate概述

&emsp;&emsp;JdbcTemplate 是Spring框架中提供的一个对象，是对原始的 Jdbc API 对象的简单封装。Spring 框架提供了很多的操作模板类。例如：操作关系型数据的 ``JdbcTemplate`` 和 ``HibernateTemplate``，操作 nosql 数据库的 ``RedisTemplate``,操作消息对列的 ``JmsTemplate`` 等等。

## 2、JdbcTemplate 开发步骤

1. 导入 ``spring-jdbc`` 和 ``spring-tx`` 的坐标。
2. 创建数据库表和实体。
3. 创建 JdbcTemplate 对象。
4. 执行数据库操作。

## 3、 JdbcTemplate 快速入门

1. 导入坐标

    ```
    <!--导入spring的jdbc坐标-->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>5.3.16</version>
    </dependency>
    
    <!--导入spring的tx坐标-->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-tx</artifactId>
        <version>5.3.16</version>
    </dependency>
    
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.32</version>
    </dependency>
    
    <dependency>
        <groupId>c3p0</groupId>
        <artifactId>c3p0</artifactId>
        <version>0.9.1.2</version>
      </dependency>
    ```
2. 创建 account 表和 Account 实体

    1. 创建 account 表
        ```
        CREATE TABLE `account`(
            `name` varchar(1024) DEFAULT NULL,
            `money` double DEFAULT NULL``
        );
        ```
        ![Account 数据报表](https://raw.githubusercontent.com/yimu-0412/image/master/image/Account%20%E6%95%B0%E6%8D%AE%E6%8A%A5%E8%A1%A8.png) 

    2. 创建 Account 实体类

        ```
        public class Account {
            private String name;
            private double money;
            //省略get和set方法}
        
            @Override
            public String toString() {
                return "Account{" +
                        "name='" + name + '\'' +
                        ", money=" + money +
                        '}';
            }
        }
        ```
3. 创建 JdbcTemplate 对象
4. 执行数据库操作

    ```
    @Test
    public void test1() throws PropertyVetoException {
        // 1.创建数据源
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/jdbctest");
        dataSource.setUser("root");
        dataSource.setPassword("0412");
        // 2.创建 JdbcTemplate 对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        // 3.设置数据源给 JdbcTenplate 对象
        jdbcTemplate.setDataSource(dataSource);
        // 4.执行操作
        int row = jdbcTemplate.update("insert into account values(?,?)", "tom", 2000);
        System.out.println(row);
    }
    ```
    &emsp;&emsp;将 JdbcTemplate 的创建权交给 Spring，将数据源 DataSource 的创建权交给 Spring，在 Spring 容器内部将数据源 DataSource 注入到JdbcTemplate 模板对象中。

    ```
    <!--数据源对象-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="com.mysql.jdbc.Drive"/>
        <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/jdbctest"/>
        <property name="user" value="root"/>
        <property name="password" value="0412"/>
    </bean>
    
    <!--jdbc模板文件-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"/>
    </bean>
    ```
    
    ```
    @Test
    public void test2(){
        // 1.创建数据源
        ApplicationContext app = new ClassPathXmlApplicationContext("appc.xml");
        // 2.创建 JdbcTemplate 对象
        // 3.设置数据源给 JdbcTenplate 对象
        JdbcTemplate jdbcTemplate = (JdbcTemplate) app.getBean("jdbcTemplate");
        // 4.执行操作：添加操作
        int row = jdbcTemplate.update("insert into account values(?,?)", "lisi", 3000);
        System.out.println(row);
    }
    ```

    &emsp;&emsp;将上面配置文件中的数据库的连接信息抽取出来放在``jdbc.properties`` 文件中，对 Spring 容器的配置进行优化：

    ```
    jdbc.driver=com.mysql.jdbc.Driver
    jdbc.url=jdbc:mysql://localhost:3306/jdbctest
    jdbc.user=root
    jdbc.password=0412
    ```
    &emsp;&emsp;对 ``application.xml``配置文件的内容进行优化：

    ```
    <!--扫描 jdbc.properties 配置文件 -->
    <context:property-placeholder location="classpath:jdbc.properties"/>
    
    <!--数据源对象-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.user}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
    
    <!--jdbc模板文件-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"/>
    </bean>
    ```

    ```
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(value = "classpath:appc.xml")
    public class JdbcTemplateCRUD {
    
        @Autowired
        @Qualifier("jdbcTemplate")
        private JdbcTemplate jdbcTemplate;
    
        @Test
        // 1. 测试修改对象
        public void testUpdate(){
            jdbcTemplate.update("update account set money =? where name =?", 10000, "tom");
        }
    
        @Test
        // 2. 测试删除对象
        public void testDelete(){
            jdbcTemplate.update("delete from account where name =?","tom");
        }
    
        @Test
        // 3. 查询操作(全部)
        public void testQueryAll(){
            List<Account> accounts = jdbcTemplate.query("select * from account",
                    new BeanPropertyRowMapper<Account>(Account.class));
            for (Account account : accounts) {
                System.out.println(account.getName());
            }
            // System.out.println(accounts);
        }
    
        @Test
        // 4. 查询操作(某个对象)
        public void testQueryOne(){
            Account forObject = jdbcTemplate.queryForObject("select * from account where name = ?",
                    new BeanPropertyRowMapper<Account>(Account.class), "lisi");
            System.out.println(forObject);
        }
    
        @Test
        // 查询操作(对象个数)
        public void testQueryCount(){
            Integer forObject = jdbcTemplate.queryForObject("select count(*) from account", Integer.class);
            System.out.println(forObject);
        }
    }`
    ```

##   4、知识要点

1.  导入 spring-jdbc 和 spring-tx 坐标。

2.  导入数据库表和实体。

3.  创建 JdbcTemplate 对象

   ````
   JdbcTemplatejdbcTemplate= new JdbcTemplate();
   
   jdbcTemplate.setDataSource(dataSource);
   ````

4.  执行数据库操作

   1. 更新操作

      ```
      jdbcTemplate.update(sql,params)
      
      jdbcTemplate.update("update account set money =? where name =?", 10000, "tom");
      ```

      2. 查询操作

         1. 查询全部数据

            ```
            jdbcTemplate.query(sql,Mapper,params)
            
            List<Account> accounts = jdbcTemplate.query("select * from account",
                            new BeanPropertyRowMapper<Account>(Account.class));
            ```

            2. 查询某个对象

               ```
               jdbcTemplate.queryForObject(sql,Mapper,params)
               
                Account forObject = jdbcTemplate.queryForObject("select * from account where name = ?",
                               new BeanPropertyRowMapper<Account>(Account.class), "lisi");
               ```

            3. 查询对象个数

               ```
               Integer forObject = jdbcTemplate.queryForObject("select count(*) from account", Integer.class);
               ```

               
