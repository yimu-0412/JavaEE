## 一、原始方式整合

### 1、准备工作

```
    create database ssm;
    create table account(
        id int primary key auto_increment,
        name varchar(100),
        money double(7,2)
    );
```

&emsp;&emsp;![SSM 整合准备工作](https://raw.githubusercontent.com/yimu-0412/image/master/image/SSM%20%E6%95%B4%E5%90%88%E5%87%86%E5%A4%87%E5%B7%A5%E4%BD%9C.png)

### 2、创建 Maven 工程

&emsp;&emsp;![SSM 整合Maven 工程目录](https://raw.githubusercontent.com/yimu-0412/image/master/image/SSM%20%E6%95%B4%E5%90%88Maven%20%E5%B7%A5%E7%A8%8B%E7%9B%AE%E5%BD%95.png)

### 3、导入 Maven 坐标

```
    <dependencies>
        <!--spring相关-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.3.16</version>
        </dependency>

        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.8.13</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.3.16</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>5.3.16</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>5.3.14</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.3.16</version>
        </dependency>

        <!--servlet和jsp-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.0</version>
        </dependency>

        <!--mybatis相关-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.5</version>
        </dependency>

        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.3.1</version>
        </dependency>

        <!--关于数据库的坐标-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.6</version>
        </dependency>

        <dependency>
            <groupId>c3p0</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.1.2</version>
        </dependency>

        <!--关于测试的坐标-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>

        <dependency>
            <groupId>jstl</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>

    </dependencies>
```

### 4、 编写实体类

```
    public class Account {
        private Integer id;
        private String name;
        private Double money;

        //省略getter和setter方法
    }
```

### 5、编写Mapper接口

```
   public interface AccountMapper {

        // 保存账户数据
        public void save(Account account);

        // 查询账户数据
        public List<Account> findAll();
    }
```

### 6、编写 Service 接口

```
    public interface AccountService {

        // 保存账户数据
        public void save(Account account) throws IOException;

        // 查询账户数据
        public List<Account> findAll();
    }
```

### 7、编写 Service 接口实现

```
    @Service("accountService")
    public class AccountServiceImpl implements AccountService {

        @Override
        public void save(Account account) throws IOException {
            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);

            AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
            this.mapper.save(account);
            sqlSession.commit();
            sqlSession.close();
        }

        @Override
        public List<Account> findAll() {
            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
            List<Account> accountList = mapper.findAll();
            return accountList;
        }
    }
```
### 8、编写 Controller

```
    @Controller
    @RequestMapping("/account")
    public class AccountController {

        @Autowired
        private AccountService accountService;

        // 保存账户数据
        @RequestMapping(value = "/save",produces = "text/html;charset=UTF-8")
        @ResponseBody
        public String save(Account account) throws IOException {
            accountService.save(account);
            return "保存成功！";
        }

        // 查询账户数据
        @RequestMapping("/findAll")
        public ModelAndView findAll(){
            List<Account> accountList = accountService.findAll();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("accountList", accountList);
            modelAndView.setViewName("accountList");
            return modelAndView;
        }
    }
```
### 9、编写添加页面

```
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h1>添加账户信息</h1>
        <form name="accountForm" action="${pageContext.request.contextPath}/account/save" method="post">
            账户名称：<input type="text" name="name"><br>
            账户金额：<input type="text" name="money"><br>
                        <input type="submit" value="保存">
        </form>
    </body>
    </html>
```


### 10、编写列表页面

```
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <html>
    <head>
        <title></title>
    </head>
    <body>
        <h1>展示账户数据列表</h1>
        <table border="1">
            <tr>
                <th>账户id</th>
                <th>账户名称</th>
                <th>账户金额</th>
            </tr>
            <c:forEach items="${accountList}" var="account">
                <tr>
                    <td>${account.id}</td>
                    <td>${account.name}</td>
                    <td>${account.money}</td>
                </tr>
            </c:forEach>

        </table>
    </body>
    </html>
```

### 11、编写相应的配置文件

* Spring 配置文件:``applicationContext.xml``

    ```
    <!--组件扫描:扫描service和mapper-->
    <context:component-scan base-package="yimu">
        <!--排除 controller 扫描-->
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <!--组件扫描-->
    <context:property-placeholder location="classpath:jdbc.properties"/>

    <!--配置数据源信息-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
    ```
* SpringMVC 配置文件:``spring-mvc.xml``

    ```
    <!--组件扫描:扫描controller-->
    <context:component-scan base-package="yimu.controller"></context:component-scan>

    <!--配置mvc的注解驱动-->
    <mvc:annotation-driven></mvc:annotation-driven>

    <!--内部资源视图解析器-->
    <bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/pages/"></property>
        <property name="suffix" value=".jsp"/>
    </bean>

    <!--静态资源访问权限-->
    <mvc:default-servlet-handler/>
    ```
* MyBatis 映射文件:``AccountMapper.xml``

    ```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace="yimu.mapper.AccountMapper">
        <select id="findAll" resultType="account">
            select * from account
        </select>

        <insert id="save" parameterType="account">
            insert into account values(#{id},#{name},#{money})
        </insert>
    </mapper>
    ```
* MyBatis 核心文件:``sqlMapConfig.xml``

    ```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>

        <!--加载 properties 文件-->
        <properties resource="jdbc.properties"></properties>

        <!--自定义类名-->
        <typeAliases>
            <typeAlias type="yimu.domain.Account" alias="account"></typeAlias>
        </typeAliases>

        <!--加载数据源文件-->
        <environments default="development">
            <environment id="development">
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
            <mapper resource="mapper\accountMapper.xml"></mapper>
        </mappers>
    </configuration>
    ```
* 数据库连接信息文件：``jdbc.properties``

    ```
    jdbc.driver=com.mysql.jdbc.Driver
    jdbc.url=jdbc:mysql://localhost:3306/ssm
    jdbc.username=root
    jdbc.password=0412
    ```
* Web.xml 文件：``web.xml``

    ```
    <?xml version="1.0" encoding="UTF-8"?>
    <web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns="http://java.sun.com/xml/ns/javaee"
            xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" id="WebApp_ID" version="2.5">

    <!--全局参数-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </context-param>

    <!--监听器-->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <!--springMvc 前端控制器-->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!--乱码过滤器-->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
        </init-param>
    </filter>

    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    </web-app>
    ```
* 日志文件：``log4j.xml``

    ```
    ### direct log messages to stdout ###
    log4j.appender.stdout=org.apache.log4j.ConsoleAppender
    log4j.appender.stdout.Target=System.out
    log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
    log4j.appender.stdout.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}:%L - %m%n

    ### direct messages to file mylog.log ###
    log4j.appender.file=org.apache.log4j.FileAppender
    log4j.appender.file.File=c:/mylog.log
    log4j.appender.file.layout=org.apache.log4j.PatternLayout
    log4j.appender.file.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}:%L - %m%n

    ### set log levels - for more verbose logging change 'info' to 'debug' ###

    log4j.rootLogger=debug, stdout
    ```
### 12、测试添加账户

&emsp;&emsp;![添加账户数据](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%B7%BB%E5%8A%A0%E8%B4%A6%E6%88%B7%E6%95%B0%E6%8D%AE.png)

### 13、测试账户列表

&emsp;&emsp;![测试账户列表](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%B5%8B%E8%AF%95%E8%B4%A6%E6%88%B7%E5%88%97%E8%A1%A8.png)

## 二、Spring 整合 MyBatis

### 1、整合思路


&emsp;&emsp;![SSM 整合思路](https://raw.githubusercontent.com/yimu-0412/image/master/image/SSM%20%E6%95%B4%E5%90%88%E6%80%9D%E8%B7%AF.png)

### 2、将 SqlSessionFactory 配置到 Spring 容器中

```
    <!--组件扫描-->
    <context:property-placeholder location="classpath:jdbc.properties"/>

    <!--配置数据源信息-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
     </bean>

    <!--配置sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!--mybatis 核心文件-->
        <property name="configLocation" value="classpath:sqlMapConfig-spring.xml"/>
        <!--mybatis 映射文件-->
        <property name="mapperLocations" value="classpath:mapper/AccountMapper.xml"/>
    </bean>

```

### 3、扫描 Mapper，让 Spring 容器产生 Mapper 实现类

```
    <!--扫描 Mapper 接口所在的包，为mapper 创建实现类-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="yimu.mapper"/>
        <!--<property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>-->
    </bean>
```
### 4、修改MyBatis核心配置文件

```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
        <!--自定义类名-->
        <typeAliases>
            <typeAlias type="yimu.domain.Account" alias="account"></typeAlias>
        </typeAliases>
    </configuration>
```
### 5、配置声明式事务控制

```
    <!--配置声明式事务控制-->
    <!--平台事务管理器-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <!--配置事务增强-->
    <tx:advice id = "txAdvice">
        <tx:attributes>
            <tx:method name="*"/>
        </tx:attributes>
    </tx:advice>

    <!--事务的aop织入-->
    <aop:config>
        <aop:advisor advice-ref="txAdvice" pointcut="execution(* yimu.service.impl.*.*(..))"></aop:advisor>
    </aop:config>
```
### 6、修改 Service 实现类代码

```
    @Service("accountService")
    public class AccountServiceImpl implements AccountService {

        @Autowired
        private AccountMapper mapper;

        @Override
        public void save(Account account){
            mapper.save(account);
        }

        @Override
        public List<Account> findAll() {
            List<Account> accountList = mapper.findAll();
            return accountList;
        }
    }
```