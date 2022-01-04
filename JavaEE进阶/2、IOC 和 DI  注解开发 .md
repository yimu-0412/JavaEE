# 一、Spring 配置数据源
## 1、数据源（连接池）的作用
* 数据源（连接池）是提高性能出现的
* 事先实例化数据源，初始化部分连接资源
* 使用连接资源时从数据源获取
* 使用完毕后将连接资源归还到数据源
&emsp;&emsp;常见的数据源：**DBCP、C3P0、BoneCP、Druid**等
## 2、数据源的开发步骤
1. 导入数据源的坐标和数据库驱动坐标
2. 创建数据源对象
3. 设置数据源的基本连接数据
4. 使用数据源获取连接资源和归还连接资源
## 3、数据源的手动创建
### 1. 手动创建连接池
1. 导入 c3p0 和 druid 的坐标

```
    <!--  C3P0连接池  -->
    <dependency>
        <groupId>c3p0</groupId>
        <artifactId>c3p0</artifactId>
        <version>0.9.1.2</version>
    </dependency>

    <!-- Druid 连接池 -->
    <!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.2.8</version>
    </dependency>    
```
2. 导入mysql数据库驱动坐标

```
    <!--mysql驱动-->
    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.27</version>
    </dependency>
```
3. 创建 c3p0 连接池

```
    @Test
    // 手动创建 c3p0 连接池
    public void testC3p0() throws Exception {
        // 1.创建数据源
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        // 2.设置数据源连接参数
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/");
        dataSource.setUser("root");
        dataSource.setPassword("0412");
        // 3.获取连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
```
4. 创建 Druid 连接池

```
    @Test
    // 手动创建 Druid 连接池
    public void testDruid() throws Exception {
        // 1.创建数据源
        DruidDataSource druidDataSource = new DruidDataSource();
        // 2.设置数据库连接参数
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("0412");
        // 3.获取连接对象
        Connection connection = druidDataSource.getConnection();
        System.out.println(connection);
    }
```
### 2.通过 propertise 配置文件创建连接池
1. 提取jdbc.properties配置文件（创建Resource Bundle 文件）

```
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/test
jdbc.username=root
jdbc.password=0412
```
2. 读取jdbc.properties配置文件创建 c3p0 连接池

```
@Test
    // 读取配置文件创建 C3P0 数据源
    public void testC3p0Propertise() throws Exception {
        // 1.加载类路径下的 jdbc.propertise 文件
        ResourceBundle rb= ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("jdbc.driver");
        String url = rb.getString("jdbc.url");
        String username = rb.getString("jdbc.username");
        String password = rb.getString("jdbc.password");
        // 2.创建数据源
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        // 3.设置数据源连接参数
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        // 4.获取数据源数据
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
```

3. 读取jdbc.properties配置文件创建 Druid 连接池

```
    @Test
    // 读取配置文件创建 Druid 数据源
    public void testDruitPropertise() throws Exception {
        // 1.加载类路径下的 jdbc.propertise 文件
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("jdbc.driver");
        String url = rb.getString("jdbc.url");
        String username = rb.getString("jdbc.username");
        String password = rb.getString("jdbc.password");
        // 2.创建数据源
        DruidDataSource druidDataSource = new DruidDataSource();
        // 3.设置数据源连接参数
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        // 4.获取数据源数据
        DruidPooledConnection connection = druidDataSource.getConnection();
        System.out.println(connection);
    }

```
## 4、Spring 配置数据源
&emsp;&emsp;可以将 DataSource 的创建权交由 Spring 容器去完成。
    
* DataSource 有无参构造方法，而 Spring 默认就是通过无参构造方法实例化对象。
* DataSource 要想使用需要通过 set 方法设置数据库连接信息，而 Spring 可以通过 set 方法进行字符串注入。

```
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <property name="driverClass" value="com.mysql.jdbc.Driver"/>
    <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/test"/>
    <property name="user" value="root"/>
    <property name="password" value="root"/>
</bean>

 @Test
    // 从 Spring 容器中获取 C3P0 数据源
    public void testC3p0Spring() throws Exception {
        ApplicationContext app = new ClassPathXmlApplicationContext("appl.xml");
        DataSource dataSource = (DataSource)app.getBean("dataSource");
        /*DataSource data = app.getBean(DataSource.class);*/
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
```

&emsp;&emsp;**applicationContext.xml加载jdbc.properties配置文件获得连接信息。**

1. 引入context命名空间和约束路径：
 
    * 命名空间：xmlns:context="http://www.springframework.org/schema/context"
    * 约束路径：xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

2. 在 appl.xml 中加载 propertise 配置文件

    ```
    <!--Spring 容器加载 propertise 配置文件-->
    <context:property-placeholder location="jdbc.properties"/>
    ```
3. 在 appl.xml 进行依赖注入

    ```
    <!--C3P0 数据源注入（加载propertise配置文件）-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>

    <!--Druid 数据源注入（加载propertise配置文件）-->
    <bean id="druid" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>

    <bean id="boneCP" class="com.jolbox.bonecp.BoneCPDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
    ```
4. 创建连接池

    ```
    @Test
    // 从 Spring 容器中获取 C3P0 数据源
    public void testC3p0Spring() throws Exception {
        ApplicationContext app = new ClassPathXmlApplicationContext("appl.xml");
        DataSource dataSource = (DataSource)app.getBean("dataSource");
        /*DataSource data = app.getBean(DataSource.class);*/
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    // 从 Spring 容器中获取 Druid 数据源
    public void testDruidSpring() throws Exception {
        ApplicationContext app = new ClassPathXmlApplicationContext("appl.xml");
        /*DataSource druid = (DataSource) app.getBean("druid");*/
        DruidDataSource druid = app.getBean(DruidDataSource.class);
        Connection connection = druid.getConnection();
        System.out.println(connection);
    }

    @Test
    // 从 Spring 容器中获取 Druid 数据源
    public void testBonecpSpring() throws Exception {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("appl.xml");
        /*BoneCPDataSource boneCP = (BoneCPDataSource) app.getBean("boneCP");*/
        BoneCPDataSource boneCP= app.getBean(BoneCPDataSource.class);
        Connection connection = boneCP.getConnection();
        System.out.println(connection);
    }
    ```
## 5、知识要点
    ```
    <context:property-placeholder location="xx.properties"/>
    <property name="" value="${key}"/>
    ```
# 二、Spring 注解开发
## 1、Spring 原始注解
&emsp;&emsp;Spring是轻代码而重配置的框架，配置比较繁重，影响开发效率，所以注解开发是一种趋势，注解代替xml配置文件可以简化配置，提高开发效率。

&emsp;&emsp;Spring原始注解主要是替代<Bean>的配置。

![Spring 原始注解标签](https://raw.githubusercontent.com/yimu-0412/image/master/image/Spring%20%E5%8E%9F%E5%A7%8B%E6%B3%A8%E8%A7%A3%E6%A0%87%E7%AD%BE.png)

**注意**：

&emsp;&emsp;使用注解进行开发时，需要在 appl.xml 中配置组件扫描，作用是**指定哪个包及其子包下的 Bean 需要进行扫描以便识别使用注解配置的类。**

    ```
    <!--注解的组件扫描-->
    <context:component-scan base-package="dataTest"/>
    ```
**Spring 原始注解的步骤**：

1. 使用 @Compont 或 @Repository 标识 UserDaoImpl 需要 Spring 进行实例化

```
    // <bean id="userDao" class="dataTest.dao.impl.UserDaoImpl"/>
    @Component("userDao")
    //@Repository("userDao")
    //@Scope("singleton")
    // @Scope("prototype") 使用@Scope标注Bean的范围
    public class UserDaoImpl implements UserDao {
        public void save() {
            System.out.println("save running···");
        }
    }
```
2. 使用@Compont或@Service标识UserServiceImpl需要Spring进行实例化,
3. 使用@Autowired或者@Autowired+@Qulifier或者@Resource进行userDao的注入
4. 使用@Value进行字符串的注入
5. 使用@PostConstruct标注初始化方法，使用@PreDestroy标注销毁方法

```
    // <bean id="service" class="dataTest.service.impl.UserServiceImpl">
    //        <property name="userDao" ref="userDao"/>
    //    </bean>
    @Component("service")
    //@Service("service")
    @Scope("singleton")
    public class UserServiceImpl implements UserService {

        /*@Autowired
        @Qualifier("userDao")*/
        
        @Resource(name="userDao")
        private UserDao userDao;

        @Value("注入普通数据")
        private String str;

        @Value("${jdbc.driver}")
        private String driver;

        /*public void setUserDao(UserDao userDao) {
            this.userDao = userDao;
        }*/

        public void save() {
            System.out.println(str);
            System.out.println(driver);
            userDao.save();
        }

        @PostConstruct
        public void init(){
            System.out.println("初始化方法···");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("销毁方法···");
        }
    }
```
## 2、Spring 新注解
&emsp;&emsp;使用 Spring 原始注解 不能全部替代 xml 文件，还需要注解替代的配置如下：

   1. 非自定义的 Bean 的配置：<bean>
   2. 加载 propertise 文件的配置：<context:property-placeholder>
   3. 组件扫描的配置：<context:component-scan>
   4. 引入其他文件：<import>

&emsp;&emsp;**Spring 新注解标签**：

![Spring 新注解标签](https://raw.githubusercontent.com/yimu-0412/image/master/image/Spring%20%E6%96%B0%E6%B3%A8%E8%A7%A3%E6%A0%87%E7%AD%BE.png)

1. @PropertySource 加载 propertise 文件，作用和 appl.xml 配置文件中<context:property-placeholder location="jdbc.properties"/> 一样
2. @Value 注入普通文件
3. @Bean 使用在方法上，返回方法的返回值存储到 Spring 容器中

```
    @PropertySource("classpath:jdbc.properties")
    public class DataSourceConfiguration {
        @Value("${jdbc.driver}")
        private String driver;
        @Value("${jdbc.url}")
        private String url;
        @Value("${jdbc.username}")
        private String username;
        @Value("${jdbc.password}")
        private String password;

        @Bean("dataSource") // 标注将该方法的返回值存储到Spring 容器中
        public DataSource getDataSource() throws PropertyVetoException {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(driver);
            dataSource.setJdbcUrl(url);
            dataSource.setUser(username);
            dataSource.setPassword(password);
            return dataSource;
        }

        @Bean("druidDataSource")
        public DruidDataSource getDruid(){
            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.setDriverClassName(driver);
            druidDataSource.setUrl(url);
            druidDataSource.setUsername(username);
            druidDataSource.setPassword(password);
            return druidDataSource;
        }
    }
```
5. @Configuration 指定当前类是一个 Spring 配置类
6. @ComponentScan 指定 Spring 在初始化容器时要扫描的包，作用和app.xml配置文件中的 <context:component-scan base-package="dataTest"/> 一样。
7. @Import 导入其他配置类

```
    @Configuration // //标志该类是Spring的核心配置类
    //<context:component-scan base-package="dataTest"/>
    @ComponentScan("dataTest")
    @Import({DataSourceConfiguration.class})

    public class SpringConfiguration {

    }
```
8. 测试加载核心配置类创建Spring容器

```
    @Test
    public void testUserServiceAnnoConfig(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        UserService service = (UserService) ac.getBean("service");
        service.save();
        ac.close();
    }

    @Test
    public void testC3p0AnnoConfig() throws SQLException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        DataSource dataSource = (DataSource) ac.getBean("dataSource");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        ac.close();
    }

    @Test
    public void testDruidAnnoConfig() throws SQLException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        DruidDataSource druidDataSource = (DruidDataSource) ac.getBean("druidDataSource");
        DruidPooledConnection connection = druidDataSource.getConnection();
        System.out.println(connection);
        ac.close();
    }
```
# 三、Spring 整合Junit
## 1、原始 Junit 测试 Spring 的问题
&emsp;&emsp;在测试类中，每个测试方法都有以下两行代码：
```
    ApplicationContext ac = newClassPathXmlApplicationContext("bean.xml");
    IAccountService as = ac.getBean("accountService",IAccountService.class);
```
&emsp;&emsp;这两行代码的作用是获取容器，如果不写的话，直接会提示空指针异常。所以又不能轻易删掉。

## 2、上述问题的解决思路

* 让SpringJunit负责创建Spring容器，但是需要将配置文件的名称告诉它
* 将需要进行测试Bean直接在测试类中进行注入

## 3、Spring 集成 Junit 步骤

1. 导入 Spring 集成 Junit 的坐标
2. 使用 @Runwith 注解替代原来的运行期
3. 使用 @ContextConfiguration 指定配置文件或者配置类
4. 使用 @Autowired 注入需要测试的对象
5. 创建测试方式进行测试
    
## 4、Spring 集成 Junit 代码实现
1. 在 pom.xml 文件中导入 Spring 集成 Junit 的坐标

    ```
    <!-- https://mvnrepository.com/artifact/junit/junit -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>

    <!-- https://mvnrepository.com/artifact/org.springframework/spring-test -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <version>5.3.14</version>
        <scope>test</scope>
    </dependency>
    ```
2. 使用 @Runwith 注解替代原来的运行期
3. 使用 @ContextConfiguration 指定配置文件或者配置类
4. 使用 @Autowired 注入需要测试的对象
5. 创建测试方式进行测试
    ```
    @RunWith(SpringJUnit4ClassRunner.class)

    //@ContextConfiguration(value={"classpath:appl.xml"})
    @ContextConfiguration(classes = {SpringConfiguration.class})

    public class SpringJunitTest {
        @Autowired
        private UserService service;
        @Test
        public void test1(){
            service.save();
        }
    }
    ```
## 5、知识要点

**Spring集成Junit步骤**
1. 在 pom.xml 文件中导入 Spring 集成 Junit 的坐标
2. 使用 @Runwith 注解替代原来的运行期
3. 使用 @ContextConfiguration 指定配置文件或者配置类
4. 使用 @Autowired 注入需要测试的对象
5. 创建测试方式进行测试