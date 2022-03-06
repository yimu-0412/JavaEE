# 一、Spring与web环境集成
1. ApplicationContext 应用上下文获取方式
    
    &emsp;&emsp;应用上下文对象是通过 ``new ClasspathXmlApplicationContext(spring配置文件)`` 方式获取的,但是每次从容器中获取Bean 时都要编写 ``new ClasspathXmlApplicationContext(spring配置文件)`` ，这样的弊端使配置文件加载多次，应用上下文对象创建多次。

     &emsp;&emsp;在Web项目中，可以使用 ``ServletContextListener`` 监听Web应用的启动，就可以在Web应用**启动时**，就**加载Spring的配置文件**，创建应用上下文对象 ``ApplicationContext``，在将其存储到最大的域``servletContext``域中，这样就可以在任意位置从域中获得应用上下文 ``ApplicationContext`` 对象。

2. Spring提供获取应用上下文的工具

    &emsp;&emsp;上面的分析不用手动实现，Spring 提供了一个监听器 ``ContextLoaderListener`` 就是对上述功能的封装，该监听器内部加载Spring配置文件，创建应用上下文对象，并存储到 ``ServletContext`` 域中，提供了一个客户端工具``WebApplicationContextUtils`` 供使用者获得应用上下文对象。

    &emsp;&emsp;需要做的只有两件事：

    1. 在 ``web.xml``中配置``ContextLoaderListener`` 监听器（导入spring-web坐标）。
    2. 使用 ``WebApplicationContextUtils`` 获得应用上下文对象 ``ApplicationContext``。

3. 导入Spring集成web的坐标

    ```
    dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-web</artifactId>
        <version>5.3.14</version>
     </dependency>
    ```
4. 配置ContextLoaderListener监听器

    ```
    <!--全局初始化参数-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:appc.xml</param-value>
    </context-param>

    <!--配置监听器-->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    ```
5. 通过工具获得应用上下文对象

    ```
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = req.getServletContext();

        // ApplicationContext app = (ApplicationContext) servletContext.getAttribute("app")

        ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        UserService userService = (UserService) app.getBean("userService");
        userService.save();
    }
    ```
6. 知识要点

    **Spring集成web环境步骤**:
    
    1. 配置 ``ContextLoaderListener`` 监听器
    2. 使用 ``WebApplicationContextUtils`` 获得应用上下文

# 二、SpringMVC 简介
## 1、SpringMVC 概述

&emsp;&emsp;SpringMVC 是一种基于Java 的实现 MVC 设计模型的请求驱动类型的轻量级 Web 框架，属于 ``SpringFrameWork`` 的后续产品，已经融合在``Spring Web Flow`` 中。

&emsp;&emsp;SpringMVC 已经成为目前最主流的 MVC 框架之一，并且随着``Spring3.0`` 的发布，全面超越 ``Struts2``，成为最优秀的 MVC 框架。它通过一套注解，让一个简单的 Java 类成为处理请求的控制器，而无须实现任何接口。同时它还支持 ``RESTful`` 编程风格的请求。

## 2、SpringMVC 快速入门开发步骤

&emsp;&emsp;需求：客户端发起请求，服务器端接收请求，执行逻辑并进行视图跳转。

1. 导入 Servlet 和 Jsp 的坐标。

    ```
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.1.0</version>
    </dependency>

    <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>javax.servlet.jsp-api</artifactId>
        <version>2.3.3</version>
    </dependency>
    ```    
2. 在 ``web.xml`` 中配置 SpringMVC 的核心控制器

    ```
    <!--配置 springMvc 的前端控制器-->
    <servlet>
        <servlet-name>DispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>DispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>      
    ```
3. 创建 Controller 和业务方法

    ```
    public class Controller {

        public String save(){
            System.out.println("controller save running····");
            return "success";
        }
    }
    ```
    在 webapp/jsp 目录中创建视图页面 ``success.jsp``
    ```
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
        <head>
            <title>Title</title>
        </head>
        <body>
            <h1>success!</h1>
        </body>
    </html>
     ```
4. 在 Controller 类中配置注解

    ```
    @Controller
    @RequestMapping("/user")
    public class Controller {

        @RequestMapping(value = "/quick")

        public String save(){
            System.out.println("controller save running····");
            return "success";
        }
    }
    ```
5. 创建 spring-mvc.xml 文件

    ```
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                    http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

        <!--配置Controller注解扫描-->
        <!--<context:component-scan base-package="yimu.controller"/>-->

        <context:component-scan base-package="yimu">
            <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
        </context:component-scan>
    </beans>
    ```
6. 访问测试地址

    ```
    http://localhost:8080/spring_mvc/user/quick
     ```
    控制台打印：

    ![SpringMVC 入门控制台打印](https://raw.githubusercontent.com/yimu-0412/image/master/image/SpringMVC%20%E5%85%A5%E9%97%A8%E6%8E%A7%E5%88%B6%E5%8F%B0%E6%89%93%E5%8D%B0.png)

    页面显示：

    ![SpringMVC 入门浏览器显示](https://raw.githubusercontent.com/yimu-0412/image/master/image/SpringMVC%20%E5%85%A5%E9%97%A8%E6%B5%8F%E8%A7%88%E5%99%A8%E6%98%BE%E7%A4%BA.png)

        
## 3、SpringMVC 流程图示

![SpringMVC 流程显示1](https://raw.githubusercontent.com/yimu-0412/image/master/image/SpringMVC%20%E6%B5%81%E7%A8%8B%E6%98%BE%E7%A4%BA1.png)

![SpringMVC 代码流程代码图示](https://raw.githubusercontent.com/yimu-0412/image/master/image/SpringMVC%20%E4%BB%A3%E7%A0%81%E6%B5%81%E7%A8%8B%E4%BB%A3%E7%A0%81%E5%9B%BE%E7%A4%BA.png)

## 4、知识要点

1. 开发步骤：

   1. 导入 SpringMVC 相关坐标
   2. 配置 SpringMVC 核心控制器 DispatcherServlet
   3. 创建 Controller 类和视图页面
   4. 使用注解配置 Controller 类中业务方法的映射地址
   5. 配置 SpringMVC 核心文件 Spring-mvc.xml
   6. 客户端发起请求测试

    ![SpringMVC 流程显示2](https://raw.githubusercontent.com/yimu-0412/image/master/image/SpringMVC%20%E6%B5%81%E7%A8%8B%E6%98%BE%E7%A4%BA2.png)

# 三、SpringMVC 组件解析
## 1、SpringMVC 的执行流程
1. 用户发送请求至前端控制器 DispatcherServlet 
2. DispatcherServlet 收到请求后调用 Handlermapping 处理器映射器
3. 处理器映射器找到具体的处理器（可以根据xml配置、注解进行查找），生成处理器对象以及处理器拦截器（如果有则生成）一并返回给 DispatcherServlet  
4. DispatcherServlet 调用 HandlerAdapter 处理器适配器
5. HandlerAdapter 经过适配调用具体的处理器（Controller，也叫后端控制器）
6. Controller 执行完成返回 ModeAndView
7. HandlerAdapter 将  Controller 执行结果 ModeAndView 返回给 DispatcherServlet
8. DispatcherServlet 将 ModeAndView 传送给 ViewResolver 视频解析器
9. ViewReslover 解析后返回具体 View
10. DispatcherServlet 进行渲染视图（即将模型数据填充至视图中）
11. DispatcherServlet 响应用户

    ![SpringMVC的执行流程](https://raw.githubusercontent.com/yimu-0412/image/master/image/SpringMVC%E7%9A%84%E6%89%A7%E8%A1%8C%E6%B5%81%E7%A8%8B.png)

## 2、SpringMVC 组件解析

1. 前端控制器：DispatcherServlet

    用户请求到达前端控制器，它就相当于MVC 模式中的C，DispatcherServlet是整个流程控制的中心，由它调用其它组件处理用户的请求，DispatcherServlet的存在降低了组件之间的耦合性

2. 处理器映射器：HandlerMapping

    HandlerMapping负责根据用户请求找到Handler 即处理器，SpringMVC提供了不同的映射器实现不同的映射方式，例如：配置文件方式，实现接口方式，注解方式等。

3. 处理器适配器：HandlerAdapter

    通过HandlerAdapter对处理器进行执行，这是适配器模式的应用，通过扩展适配器可以对更多类型的处理器进行执行。

4. 处理器：Handler

    它就是我们开发中要编写的具体业务控制器。由DispatcherServlet把用户请求转发到Handler。由 Handler 对具体的用户请求进行处理。
5. 视图解析器：View Resolver

    View Resolver 负责将处理结果生成View 视图，View Resolver 首先根据逻辑视图名解析成物理视图名，即具体的页面地址，再生成View 视图对象，最后对View 进行渲染将处理结果通过页面展示给用户。

6. 视图：View

    SpringMVC框架提供了很多的View 视图类型的支持，包括：jstlView、freemarkerView、pdfView等。最常用的视图就是jsp。一般情况下需要通过页面标签或页面模版技术将模型数据通过页面展示给用户，需要由程序员根据业务需求开发具体的页面

## 3、SpringMVC 注解解析

1. @RequestMapping

   1. 作用：用于建立请求 URL 和处理请求方法之间的对应关系
   2. 位置：
      * 类上：请求 URL 的第一级访问目录。此处不写的话，就相当于应用的根目录。
      * 方法上：请求 URL 上的第二级目录。与类上的使用 @RequestMapping 标注的一级目录一起组成访问虚拟路径。
   3. 属性

      * value：用于指定请求的 URL。它和 path 的属性是一样的。
      * method：用于指定请求的方式。
      * params：用于指定限制请求参数的条件。支持简单的表达式，要求请求参数的 key 和 value 必须和配置的一模一样。
        * params={"accountName"}:表示请求参数中必须有 accountName。
        * params={"money!100"}：表示请求参数中 money 不能是100。

2. context 空间的引入

    ```
    <beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
               http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
    ```
   
3. 组件扫描

    SpringMVC 基于Spring容器，所以在进行 SpringMVC 操作时，需要将 Controller 存储到 Spring 容器中，如果使用 @Controller 注解标注的话，就需要使用
    
    ```
    <!--Controller组件扫描-->
    <!--<context:component-scan base-package="yimu.controller"/>-->

    <context:component-scan base-package="yimu">
        <context:include-filter type="annotation" expression="orgspringframework.stereotype.Controller"/>
    </context:component-scan>
    ```

## 4、SpringMVC 的XML配置解析

3. 视图解析器

    SpringMVC有默认组件配置，默认组件都是 ``DispatcherServlet.properties`` 配置文件中配置的，该配置文件地址 ``org/springframework/web/servlet/DispatcherServlet.properties``，该文件中配置了默认的视图解析器，如下

    ```
    org.springframework.web.servlet.ViewResolver=org.springframework.web.servlet.view.InternalResourceViewResolver
    ```
    翻看该解析器源码，可以看到该解析器的默认设置，如下：
    ```
    REDIRECT_URL_PREFIX = "redirect:" --重定向前缀
    FORWARD_URL_PREFIX = "forward:" --转发前缀（默认值）
    prefix = ""; --视图名称前缀
    suffix = ""; --视图名称后缀
    ```

    可以通过属性注入的方式修改视图的前后缀(spring-mvc.xml)：

    ```
    <!--配置内部资源视图解析器-->
    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!-- /jsp/success.jsp-->
        <property name="prefix" value="/jsp/"></property>
        <property name="suffix" value=".jsp"></property>
    </bean>
    ```
## 5、知识要点

1. SpringMVC 相关组件
   1. 前端控制器：DispatcherServle  
   2. 处理器映射器：HandlerMapping
   3. 处理器适配器：HandlerAdapter
   4. 处理器：Handler
   5. 视图解析器：View Resolver
   6. 视图：View
2. SpringMVC 的注解和配置
   1. 请求映射注解：@ResquestMapping
   2. 视图解析器配置：

        ```
        REDIRECT_URL_PREFIX = "redirect:"
        FORWARD_URL_PREFIX = "forward:"
        prefix = "";
        suffix = "";
        ```