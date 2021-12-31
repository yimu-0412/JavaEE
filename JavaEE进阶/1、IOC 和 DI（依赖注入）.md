# 一、Spring5 简介
## 1、什么是 Spring？
1. Spring 是分层的 JavaSE/EE 应用 full-stack 轻量级的开源框架，以 **IOC**（Inverse of Control）和 **AOP**(Aspect Oriented Programming：面向切面编程)为内核。提供了**展现层SpringMVC**和**持久层Spring JDBCTemplate**以及**业务层事务管理**等众多的企业级应用技术，还能整合开源世界众多著名的第三方框架和类库，逐渐成为使用最多的Java EE 企业应用开源框架。
2. Spring 可以解决企业应用的复杂性。
3. Spring 两个核心部分：IOC 和 AOP
   
   * IOC：控制翻转，把创建对象过程交给 Spring 进行管理。
   * AOP：面向切面，不修改源代码进行功能增强。
  
## 2、Spring 的优势
1. 方便解耦，简化开发；
2. AOP 编程的支持；
3. 声明式事务的支持；
4. 方便程序的测试；
5. 方便集成各种优秀的框架；

    Spring对各种优秀框架（Struts、Hibernate、Hessian、Quartz等）的支持。

6. 降低 JavaEE API 的使用难度；
7. Java 源码是经典的学习范例。

## 3、Spring 的体系结构

![Spring 的体系结构](https://raw.githubusercontent.com/yimu-0412/image/master/image/Spring%20%E7%9A%84%E4%BD%93%E7%B3%BB%E7%BB%93%E6%9E%84.png)

## 4、Spring 快速入门
### 1. Spring 程序开发步骤

![Spring 开发步骤图示](https://raw.githubusercontent.com/yimu-0412/image/master/image/Spring%20%E5%BC%80%E5%8F%91%E6%AD%A5%E9%AA%A4%E5%9B%BE%E7%A4%BA.png) 

1. 导入 Spring 开发的基本包坐标
2. 编写 Dao 接口和实现类
3. 创建 Spring 核心配置文件
4. 在 Spring 配置文件中配置 UserDaolmpl
5. 使用 Spring 的 API 获得 Bean 实例

### 2. 导入 Spring 开发的基本包坐标

```
    <properties>
        <spring.version>5.0.5.RELEASE</spring.version>
    </properties>

    <dependencies>
        <!--导入spring的context坐标，context依赖core、beans、expression-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
    </dependencies>
```

## 5、Spring 配置文件
### 1. Bean
#### 1. Bean 标签基本配置
&emsp;&emsp;用于配置对象交由Spring来创建。
&emsp;&emsp;默认情况下它调用的是类中的**无参构造函数**，如果没有无参构造函数则不能创建成功。

&emsp;&emsp;基本属性：
* id：Bean 实例在 Spring 容器中的唯一标识。
* class：Bean 的全限定名称。

#### 2. Bean 标签的范围配置

&emsp;&emsp;scope：指对象的作用范围，取值如下：

![Bean 标签的取值范围](https://raw.githubusercontent.com/yimu-0412/image/master/image/Bean%20%E6%A0%87%E7%AD%BE%E7%9A%84%E5%8F%96%E5%80%BC%E8%8C%83%E5%9B%B4.png)

1. **当 scope 的值为 singleton 时**

&emsp;&emsp;Bean 的实例化个数：1个

&emsp;&emsp;Bean 的实例化时机：当 Spring 核心文件被加载时，实例化配置的 Bean 实例。

&emsp;&emsp;Bean 的生命周期：

        1. 对象创建：当应用加载，创建容器时，对象被创建。
        2. 对象运行：只要容器在，对象一直存活。
        3. 对象销毁：当应用卸载，销毁容器时，对象被销毁。

2. **当 scope 的值为 prototype 时**

&emsp;&emsp;Bean的实例化个数：多个。

&emsp;&emsp;Bean的实例化时机：当调用getBean()方法时实例化 Bean。

&emsp;&emsp;Bean 的生命周期：

        1. 对象创建：当使用对象时，创建新的对象实例。
        2. 对象运行：只要对象在使用中，就一直活着。
        3. 对象销毁：当对象长时间不用时，被Java 的垃圾回收器回收。

#### 3. Bean 生命周期的配置

* init-method: 指定类中的初始化方法名称。
* destroy-method: 指定类中销毁方法的名称。

#### 4. Bean 实例化的三种方法

* 无参构造方法实例化

    ```
        <!--  无参构造方法实例化  -->
        <bean id="ud" class="Dao.impl.UserDaoImpl"></bean>
    ```
* 工厂静态方法实例化

    ```
        public class StaticFactoryBean {
            public static UserDao creatEUserDao(){
                return new UserDaoImpl();
            }
        }

        <!--  工厂静态方法实例化  -->
        <bean id="ud" class="factory.StaticFactoryBean" factory-method="creatEUserDao"/>
    ```
* 工厂实例方法实例化

    ```
        public class DynamicFactoryBean {
            public DynamicFactoryBean() {
            }

            public UserDao createUserDao(){
                return new UserDaoImpl();
            }
        }

        <!--  工厂非静态方法实例化  -->
        <bean id="factpryBean" class="factory.DynamicFactoryBean" />
        <bean id="ud" factory-bean="factpryBean" factory-method="createUserDao"/>
    ```

![Bean 实例化方式](https://raw.githubusercontent.com/yimu-0412/image/master/image/Bean%20%E5%AE%9E%E4%BE%8B%E5%8C%96%E6%96%B9%E5%BC%8F.png)

#### 5. Bean 的依赖注入
1. 入门

    1. 创建UserService，UserService 内部在调用UserDao的save() 方法

         ```
        public class UserServiceImpl implements UserService {

            @Override
            public void save() {
                ApplicationContext app = new
                        ClassPathXmlApplicationContext("app.xml");
                UserDao userDao = (UserDao)app.getBean("ud");
                userDao.save();
            }
        }
        ```
    2. 将 UserServiceImpl 的创建权交给 Spring

        ```
        <bean id="us" class="service.impl.UserServiceImpl"/>
        ```
    3. 从Spring 容器中获得UserService 进行操作

        ```
        public class UserController {
            public static void main(String[] args) {
                ApplicationContext app = new
                        ClassPathXmlApplicationContext("app.xml");
                UserService service =(UserService) app.getBean("us");
                service.save();
            }
        }
        ```
2. 分析

&emsp;&emsp;目前UserService实例和UserDao实例都存在与Spring容器中，当前的做法是在容器外部获得UserService实例和UserDao实例，然后在程序中进行结合。

![Bean 的依赖注入分析1](https://raw.githubusercontent.com/yimu-0412/image/master/image/Bean%20%E7%9A%84%E4%BE%9D%E8%B5%96%E6%B3%A8%E5%85%A5%E5%88%86%E6%9E%901.png)

&emsp;&emsp;因为UserService和UserDao都在Spring容器中，而最终程序直接使用的是UserService，所以可以在Spring容器中，将UserDao设置到UserService内部。

![Bean 的依赖注入分析2](https://raw.githubusercontent.com/yimu-0412/image/master/image/Bean%20%E7%9A%84%E4%BE%9D%E8%B5%96%E6%B3%A8%E5%85%A5%E5%88%86%E6%9E%902.png)

3. 概念

&emsp;&emsp;**依赖注入（dependency）**： 它是 Spring 框架核心 IOC 的具体体现。

&emsp;&emsp;编写程序时，通过控制反转，把对象的创建交给 Spring，但是代码中不可能出现没有依赖的情况。IOC 解耦只是降低依赖关系，但是不会消除。

4. 方式 

   1. set 方法注入

        ```
            // 在UserServiceImpl中添加setUserDao方法
            public class UserServiceImplimplements UserService{
                private UserDao userDao;
                public void setUserDao(UserDao userDao) {
                    this.userDao = userDao;
                }
                
                @Override
                public void save() {
                    userDao.save();
                }
            }

            // 配置Spring容器调用set方法进行注入
            <bean id="ud" class="Dao.impl.UserDaoImpl"/>

            <bean id="us" class="service.impl.UserServiceImpl">
                <property name="userDao" ref="ud"/>
            </bean>

            // P 命名空间注入本质也是set方法注入，但是比set方法更简洁。主要体现在配置文件中
            // 首先，需要引入 p 命名空间：
            xmlns:p="http://www.springframework.org/schema/p"
            // 其次，修改注入方式：
            <bean id="us" class="service.impl.UserServiceImpl" p:userDao-ref="ud"></bean>
        ```
   2. 构造方法注入

        ```
        // 创建有参构造
        public class UserServiceImpl implements UserService {
            private UserDao userDao;

            public UserServiceImpl(UserDao userDao) {
                this.userDao = userDao;
            }

            @Override
            public void save() {
                ApplicationContext app = new
                        ClassPathXmlApplicationContext("app.xml");
                UserDao userDao = (UserDao)app.getBean("ud");
                userDao.save(); 
            }
        }

        // 配置Spring容器调用有参构造时进行注入
        <bean id="ud" class="Dao.impl.UserDaoImpl"/>

        <bean id="us" class="service.impl.UserServiceImpl">
            <constructor-arg name="userDao" ref="ud"></constructor-arg>
        </bean>
        ```

1. 数据类型

   1. 普通数据类型

    ```
        public class UserDaoImpl implements UserDao{
            private String company;
            private int age;

            public void setCompany(String company) {
                this.company = company;
            }
            public void setAge(int age) {
                this.age = age;
            }
            public void save() {
                System.out.println(company+"==="+age);
                System.out.println("UserDao save method running....");
            }
        }

        <bean id="userDao" class= "com.itheima.dao.impl.UserDaoImpl">
            <property name= "company" value= "以牧"></property>
            <property age= "age" value= "12"></property>
        </bean>
    ```
   2. 引用数据类型
   3. 集合数据类型

      1. List<String> 的注入

            ```
            public class UserDaoImpl implements UserDao {
                private List<String> strList;
                public void setStrList(List<String> strList) {
                    this.strList = strList;
                }
                public void save() {
                    System.out.println(strList);
                    System.out.println("UserDao save method running....");
                }
            }

            <!-- 集合数据类型 List<String> 的注入-->
            <bean id="ud" class="Dao.impl.UserDaoImpl">
                <property name="strList">
                    <list>
                        <value>aaa</value>
                        <value>bbb</value>
                        <value>ccc</value>
                    </list>
                </property>
            </bean>
            ```
      2. List<User> 的注入

            ```
            public class User {
                private String name;
                private int age;

                public void setName(String name){
                    this.name = name;
                }

                public void setAge(int Age){
                    this.age = age;
                }

                @Override
                public String toString() {
                    return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
                }
            }

            public class UserDaoImpl implements UserDao {
                private List<User> userList;

                public void setUserList(List<User> userList){
                    this.userList = userList;
                }

                public void save(){
                    System.out.println(userList);
                    System.out.println("userDao save method running ···")
                }
            }

            <--集合数据 List<User> 的注入-->
            <bean id ="u1" class ="domain.User"></bean>
            <bean id ="u2" class ="domain.User"></bean>

            <bean id ="ud" class ="Dao.impl.UserDaoImpl">
                <property name ="userList">
                    <list>
                        <bean class="domain.User"/>
                        <ref bean ="u1"/>
                        <bean class="domain.User"/>
                        <ref bean ="u2"/>
                    </list>
                </property>
            </bean>
            ```
        3. Map<String,User> 的注入

            ```
            public class UserDaoImpl implements UserDao {
                private Map<String,User> userMap;

                public void setUserMap(Map<String,User> userMap){
                    this.userMap = userMap;
                }

                public void save(){
                    System.out.println(userMap);
                    System.out.println("userDao save method running ···")
                }
            }

            <!-- 集合数据 Map<String,User> 的注入  -->
            <bean id="u1" class="domain.User">
                <property name="name" value="陆成"/>
                <property name="age" value="19"/>
            </bean>

            <bean id="u2" class="domain.User">
                <property name="name" value="大黄"/>
                <property name="age" value="10"/>
            </bean>

            <bean id="ud" class="Dao.impl.UserDaoImpl">
                <property name="userMap">
                    <map>
                        <entry key ="um1" value-ref ="u1"/>
                        <entry key ="um2" value-ref ="u2"/>
                    </map>
                </property>
            </bean>
            ```
        4. Propertise 的注入

            ```
            public class UserDaoImpl implements UserDao {
                private Propertise propertise;

                public void setPropertise(Propertise propertise){
                    this.propertise = propertise;
                }

                public void save(){
                    System.out.println(propertise);
                    System.out.println("userDao save method running ···")
                }
            }

            <!-- 集合数据 Propertise 的注入  -->
            <bean id="u1" class="domain.User">
                <property name="name" value="陆成"/>
                <property name="age" value="19"/>
            </bean>

            <bean id="u2" class="domain.User">
                <property name="name" value="大黄"/>
                <property name="age" value="10"/>
            </bean>

            <bean id="ud" class="Dao.impl.UserDaoImpl">
                <property name ="propertise">
                    <props>
                        <prop key ="p1">aaa</prop>
                        <prop key ="p2">ddd</prop>
                        <prop key ="p2">fff</prop>
                    </props>
                </property>
            </bean>
            ```
### 2. 引入其他的配置文件(分模块开发)

&emsp;&emsp;实际开发中，Spring的配置内容非常多，这就导致 Spring 配置很繁杂且体积很大，所以，可以将部分配置拆解到其他配置文件中，而在Spring 主配置文件中通过``import``标签进行加载。

```
    <import resource= "applicationContext-xxx.xml"/>
```
## 6、Spring 相应的 API

### 1. ApplicationContext 的继承体系

&emsp;&emsp;**ApplicationContext**：**接口类型**，代表应用上下文，可以通过其实例获得 Spring 容器中的 Bean 对象。

![ApplicationContext继承体系](https://raw.githubusercontent.com/yimu-0412/image/master/image/ApplicationContext%E7%BB%A7%E6%89%BF%E4%BD%93%E7%B3%BB.jpg)
### 2. ApplicationContext 的实现类
1. ClassPathXmlApplicationContext

    &emsp;&emsp;它是从类的根路径下加载配置文件。推荐使用这种。

2. FileSystemXmlApplicationContext

    &emsp;&emsp;它是从磁盘路径上加载配置文件，配置文件可以在磁盘的任意位置。

3. AnnotationConfigApplicationContext

    &emsp;&emsp;当使用注解配置容器对象时，需要使用此类来创建 Spring 容器。它用来读取注解。

### 3. getBean()方法使用

```
    public Object getBean(String s) throws BeansExxpection{
        assertBeanFactoryActive();
        return getBeanFactory.getBean(name);
    }

    public <T> T getBean(Class<T> requiredType) throws BeansException{
        assertBeanFactoryActive();
        return getBeanFactory().getBean(requiredType);
    }
```

&emsp;&emsp;**当参数的数据类型是字符串时**，表示根据 Bean 的``id``从容器中获得 Bean 实例，返回是 Object，**需要强转**。

&emsp;&emsp;**当参数的数据类型是Class类型时**，表示根据类型从容器中匹配 Bean 实例，当**容器中存在相同类型的Bean有多个时，此方法会报错**。

### 4. 知识要点

&emsp;&emsp;**Spring 的重点 API**

```
    ApplicationContext app = new ClassPathXmlApplicationContext("xml文件")；
    app.getBean("id");
    app.getBean(Class);
```
# 二、IOC
# 三、AOP
# 四、JdbcTemplate
# 五、事务操作
# 六、Spring5 框架新功能