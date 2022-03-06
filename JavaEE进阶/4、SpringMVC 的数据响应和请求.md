# 一、SpringMVC 的数据响应
## 1、页面跳转
1. 直接返回字符串

   直接返回字符串:此种方式将返回字符串与视图解析器的前后缀拼接后跳转。

    视图解析器：

    ```
    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
            <!-- /jsp/success.jsp-->
            <property name="prefix" value="/jsp/"></property>
            <property name="suffix" value=".jsp"></property>
        </bean>
    ```

    ```
    // @RequestMapping(value = "/quick",method = RequestMethod.GET,params = {"username"})
    @RequestMapping(value = "/quick1")
    public String save1(){

        System.out.println("controller save running····");
        // 返回字符串：转发
        // return "success";
        // 返回字符串：重定向
        return "redirect:/index.jsp";
    }
    ```

    ![字符串和视图解析器前后缀拼接](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%AD%97%E7%AC%A6%E4%B8%B2%E5%92%8C%E8%A7%86%E5%9B%BE%E8%A7%A3%E6%9E%90%E5%99%A8%E5%89%8D%E5%90%8E%E7%BC%80%E6%8B%BC%E6%8E%A5.png)

    返回带有前缀的字符串：

    1. 转发：``forward:/WEB-INF/views/index.jsp``
    2. 重定向：``redirect:/index.jsp``

2. 通过 ModelAndView 对象返回

    ```
    @RequestMapping(value = "/quick2")
    public ModelAndView save2(){
        ModelAndView modelAndView = new ModelAndView();
        /*
        * Model:模型 作用封装数据
        * View :视图 作用展示数据
        */
        // 设置模型数据
        // modelAndView.addObject("username","itcase");
        // 设置视图名称
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping(value = "/quick3")
    public ModelAndView save3(){
        ModelAndView modelAndView = new ModelAndView();
        /*
        * Model:模型 作用封装数据
        * View :视图 作用展示数据
        */
        // 设置模型数据
        // modelAndView.addObject("username","itcase");
        // 设置视图名称
        modelAndView.setViewName("redirect:index.jsp");
        return modelAndView;
    }
    ```

    直接传入 ModelAndView 对象，不创建 ModelAndView 实例
    ```
    @RequestMapping(value = "/quick3")
    public ModelAndView save3(ModelAndView modelAndView){
        // 设置模型数据
        modelAndView.addObject("username","hahaha");
        // 设置视图名称
        modelAndView.setViewName("redirect:index.jsp");
        return modelAndView;
    }
    ```

3. 向 request 域中存储数据
   1. 通过SpringMVC 框架注入 request 对象 setAtttibute() 方法设置

        ```
        @RequestMapping(value = "/quick5")
        public String save5(HttpServletRequest request){
            request.setAttribute("username","zhansan");
            return "success";
        }
        ```
    2. 通过将 MOdel 和 View 拆开，返回字符串

        ```
        @RequestMapping(value = "/quick4")
        public String save4(Model model){
            model.addAttribute("username","博学");
            return "success";
        }
        ```
    3. 通过 ModelAndView 的 addObject() 方法设置

        ```
        @RequestMapping(value = "/quick2")
        public ModelAndView save2(){
            ModelAndView modelAndView = new ModelAndView();
            // 设置模型数据
            modelAndView.addObject("username","itcase");
            // 设置视图名称
            modelAndView.setViewName("success");
            return modelAndView;
        }
        ```
## 2、回写数据
1. 直接返回字符串

    1. 通过SpringMVC框架注入的response对象，使用response.getWriter().print(“hello world”) 回写数据，此时**不需要视图跳转**，业务方法返回值为void。

        ```
        @RequestMapping("/quickMethod1")
        public void quickMethod1(HttpServletResponse response) throws IOException {
            response.getWriter().write("hello world");
        }
        ```

    2. 将需要回写的字符串直接返回，但此时需要通过``@ResponseBody``注解告知SpringMVC框架，方法返回的字符串**不是跳转而是直接在http响应体中返回**。

        ```
        @RequestMapping("/quickMethod2")
        @ResponseBody // 告知 SpringMVC 框架，不进行视图跳转，直接进行数据响应
        public String quickMethod2() throws IOException {
            return "hello SpringMVC";
        }
        ```
    3. 返回 json 格式字符串

        在异步项目中，客户端和服务器往往会通过 json 格式字符串交互，此时手动拼接比较麻烦，开发中使用web阶段的 json 转换工具 jackson 进行转换，导入 jackson 坐标。

        ```
        <dependency>
             <groupId>com.fasterxml.jackson.core</groupId>
             <artifactId>jackson-core</artifactId>
             <version>2.13.0</version>
         </dependency>

         <dependency>
             <groupId>com.fasterxml.jackson.core</groupId>
             <artifactId>jackson-databind</artifactId>
             <version>2.13.0</version>
         </dependency>

         <dependency>
             <groupId>com.fasterxml.jackson.core</groupId>
             <artifactId>jackson-annotations</artifactId>
             <version>2.13.0</version>
         </dependency>
        ```

        ```
        @RequestMapping("/quickMethod3")
        @ResponseBody // 告知 SpringMVC 框架，不进行视图跳转，直接进行数据响应
        public String quickMethod3() throws IOException {
            User user = new User();
            user.setName("lisan");
            user.setAge(30);
            // 使用 json 转换工具将对象转换成 json 格式字符串再返回
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(user);
            return json;
        }
        ```
2. 返回对象或集合

    &emsp;&emsp;在方法上添加 @ResponseBody 既可以返回 json 格式的字符串，但是这样的配置比较麻烦，配置的代码比较多，因此可以使用 mvc 的注解驱动来代替。

    ```
    <--mvc 的注解驱动-->
    <mvc:annotation-driven/>
    ```

    &emsp;&emsp;在 mvc 的各个组件中，处理器映射器、处理器适配器、视图解析器称为 SpringMVC 的三大组件。使用 ``<mvc:annotation-driven/>``自动加载 RequestMappingHandlerMapping（处理映射器）和RequestMappingHandlerAdapter（处理器适配器），可用在 Spring-mvc.xml 配置文件中使用``<mvc:annotation-driven/>``替代注解处理器和适配器的位置。同时使用 ``<mvc:annotation-driven/>``默认底层就会集成 jackson 进行对象或者集合的 json 格式字符串的转换。

    ```
     // 回写数据: 1.直接返回集合或对象（json 格式）
    // 期望 SpringMVC 框架自动将 User 转换成 json 格式的字符串
    @RequestMapping("/quickMethod4")
    @ResponseBody // 告知 SpringMVC 框架，不进行视图跳转，直接进行数据响应
    public User quickMethod4() throws IOException {
        User user = new User();
        user.setName("lisan");
        user.setAge(90);
        return user;
    }
    ```

    通过 http://127.0.0.1:8080/spring_mvc/quickMethod4 访问，看到如下结果：

    ![mvc注解驱动的显示效果](https://raw.githubusercontent.com/yimu-0412/image/master/image/mvc%E6%B3%A8%E8%A7%A3%E9%A9%B1%E5%8A%A8%E7%9A%84%E6%98%BE%E7%A4%BA%E6%95%88%E6%9E%9C.png)

# 二、SpringMVC 获得请求数据

## 1、获得请求参数
&emsp;&emsp;客户端请求参数的格式是：``name=value&name=value···``

&emsp;&emsp;服务器要获得请求的参数，有时需要进行数据的封装 SpringMVC 可以接收如下类型的参数：
    
1.  基本参数类型
2.  POJO 类型参数
3.  数组类型参数
4.  集合类型参数

## 2、获得基本类型参数

&emsp;&emsp;Controller 的业务方法的参数名称和请求参数的name一致，参数会自动映射匹配。

```
    http://localhost:8080/spring_mvc/user/quick1?name=zhangsan&age=12
```

```
    // 1.获取基本参数类型
    @RequestMapping("/quick1")
    @ResponseBody
    public void quickMethod1(String name,int age){
        System.out.println(name);
        System.out.println(age);
    }
```
&emsp;&emsp;获取基本参数如下：

![获取基本参数](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%8E%B7%E5%8F%96%E5%9F%BA%E6%9C%AC%E5%8F%82%E6%95%B0.png)

## 3、获得 JOPO 类型参数
&emsp;&emsp;Controller 的业务方法的参数名称和请求参数的name一致，参数会自动映射匹配。

```
    http://localhost:8080/spring_mvc/user/quick2?name=zhangsan&age=12
```
```
    public class User {
        private String username;
        private int age;
        getter/setter…
    }

    // 2.获得 POJO 类型数据
    @RequestMapping("/quick2")
    @ResponseBody
    public void quickMethod2(User user){
        System.out.println(user);
    }
```
![获取 JOPO 类型参数](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%8E%B7%E5%8F%96%20JOPO%20%E7%B1%BB%E5%9E%8B%E5%8F%82%E6%95%B0.png)

## 4、获取数组类型参数

&emsp;&emsp;Controller 的业务方法的参数名称和请求参数的name一致，参数会自动映射匹配。

```
    http://localhost:8080/spring_mvc/user/quick3?strs=1&strs=2&strs=zhang

```
```
    // 3.获得数组类型参数
    @RequestMapping("/quick3")
    @ResponseBody
    public void quickMethod3(String[] strs){
        System.out.println(Arrays.asList(strs));
    }
```
![获取数组类型参数](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%8E%B7%E5%8F%96%E6%95%B0%E7%BB%84%E7%B1%BB%E5%9E%8B%E5%8F%82%E6%95%B0.png)

## 5、获得集合数据类型
1. 通过 form 标签获得集合参数时，要将集合参数包装到一个 POJO 中才可以。

    ```
   <form action="${pageContext.request.contextPath}/user/quick4" method="post">
        <input type="text" name="userList[0].name"> <br>
        <input type="text" name="userList[0].age"> <br>
        <input type="text" name="userList[1].name"><br>
        <input type="text" name="userList[1].age"><br>
        <input type="submit" value="提交"><br>
    </form> 

    ```

    ```
    public class VO {
        private List<User> userList;

        public List<User> getUserList() {
            return userList;
        }

        public void setUserList(List<User> userList) {
            this.userList = userList;
        }

        @Override
        public String toString() {
            return "VO{" +
                    "userList=" + userList +
                    '}';
        }
    }
    ```
    ```
    // 4.获取集合类型数据: 通过 form 提交时，一般将集合封装到一个POJO中
    // 通过配置 filter 全局配置进行字符集编码的设定
    @RequestMapping("/quick4")
    @ResponseBody
    public void quickMethod4(VO vo){
        System.out.println(vo);
    }
    ```

    在 web.xml 文件中配置 filter 全局过滤进行字符集编码的设定
    ```
    <!--配置全局过滤的 filter-->
    <filter>
        <filter-name>CharacterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>

    <filter-mapping>
        <filter-name>CharacterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    ```
2. 当使用ajax提交时，可以指定contentType为json形式，那么在方法参数位置使用 @RequestBody 可以直接接收集合数据而无需使用POJO进行包装。

    ```
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>

    <script>
        var userList = new Array();
        userList.push({name:"zhangsan",age:19});
        userList.push({name:"lisi",age:45});

        $.ajax({
            type:"post",
            contentType:"application/json;charset=UTF-8",
            url:"${pageContext.request.contextPath}/user/quick5",
            data:JSON.stringify(userList),
        });
    </script>
    ```

    ```
    // 4.获取集合类型数据: 通过 ajax 提交
    // @RequestBody 格式必须是 json 格式的
    @RequestMapping("/quick5")
    @ResponseBody
    public void quickMethod5(@RequestBody List<User> userList){
        System.out.println(userList);
    }
    ```
    **注意**：通过浏览器开发者工具抓包发现，没有加载到 jquery 文件，原因是 SpringMVC 前端控制器 DispatcherServlet 的url-patterm 配置的是 /，代表所有文件都进行过滤，可以同通过以下两种方式指定放行静态资源：

    * 在 Spring-mvc.xml 配置文件中指定放行的资源

        ```
        <!--开放资源的访问（一般是静态资源）-->
        <!--mapping 时服务端找资源的地址，laction 是具体找资源的目录-->
        <!--<mvc:resources mapping="/js/**" location="/js/"/>
        <mvc:resources mapping="/img/**" location="/img/"/>-->
        ```
    * 使用 ``<mvc:default-servlet-handler/>标签

## 6、请求数据乱码问题

&emsp;&emsp;当post请求时，数据会出现乱码，我们可以设置一个过滤器来进行编码的过滤。

```
    <!--配置全局过滤的 filter-->
    <filter>
        <filter-name>CharacterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>

    <filter-mapping>
        <filter-name>CharacterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```

## 7、参数绑定注解 @RequestParam

&emsp;&emsp;当请求的参数名称与 Controller 的业务方法参数名称不一样时，就需要通过 @RequestParam 注解显示的绑定。

```
    // 5.参数绑定注解 @RequestParam
    // required: 当前指定的参数是否必须包括，默认是 true，提交时如果没有此参数则会报错
    // defaultValue: 当没有指定请求参数时，则使用指定的默认值赋值
    @RequestMapping("/quick6")
    @ResponseBody
    public void quickMethod6(@RequestParam(value="name",required = false,defaultValue = "itcase") String username){
        System.out.println(username);
    }
``` 
&emsp;&emsp;**@RequestParam的参数：**

* value：请求参数的名称
* required：当前指定的参数是否必须包括，默认是 true；提交时如果没有此参数会报错。

    ![@RequestParam 报错显示](https://raw.githubusercontent.com/yimu-0412/image/master/image/%40RequestParam%20%E6%8A%A5%E9%94%99%E6%98%BE%E7%A4%BA.png)

* defaultValue：当前没有请求参数时，则使用指定的默认值赋值。

## 8、获得 Restful 风格的参数

&emsp;&emsp; Restful 风格是一种**架构风格、设计风格**，而不是标准只是提供了一组设计原则和约束条件。主要用于客户端和服务端交互类的软件，基于这个风格设计的软件可以更简洁，更有层次，更易于实现缓存机制等。

&emsp;&emsp;Restful 风格的请求是使用 “URL+请求方式” 表示一次请求目的，HTTP 协议中四个表示操作动作的动词如下：

* GET: 用于获取资源
* POST: 用于新建资源
* PUT: 用于更新资源
* DELETE: 用于删除资源  

&emsp;&emsp;**例如**：

   * /user/1 GET: 得到 id=1 的user；
   * /user/1 DELETE：删除 id=1 的user
   * /user/1 PUT:更新 id=1 的user
   * /user   POST:新增 user

&emsp;&emsp;以上 url 地址 /user/1 中的1就是要获得的请求参数，在 SpringMVC 中可以使用占位符进行参数绑定。地址 /user/1 可以写成 /user/(id),占位符(id）对应的就是1的值，在业务方法中我们使用@PathVariable 注解进行占位符的匹配获取工作。

```
// 6.获得 Restful 风格的参数
    @RequestMapping("/quick7/{username}")
    @ResponseBody
    public void quickMethod7(@PathVariable(value = "username") String username){
        System.out.println(username);
    }

```
&emsp;&emsp;@PathVariable 的参数：

* required：当前指定的参数是否必须包括，默认是 true；提交时如果没有此参数会报错。

    ![@Restful 风格的报错显示](https://raw.githubusercontent.com/yimu-0412/image/master/image/%40Restful%20%E9%A3%8E%E6%A0%BC%E7%9A%84%E6%8A%A5%E9%94%99%E6%98%BE%E7%A4%BA.png)

## 9、自定义类型转换器

&emsp;&emsp;SpringMVC 已经提供了一些常用的类型转换器，例如客户端提交的字符串转换成 int 型进行参数设置。

&emsp;&emsp;但是不是所有的数据类型都提供了转换器，没有提供的就需要自定义转换器，例如：日期类型的数据类型就需要自定义转换器。

&emsp;&emsp;自定义类型转换器的开发步骤：

1. 定义转换器实现 Converter 接口。
2. 在配置文件中声明转换器。
3. 在``<mvc:annotation-driven/>``中引用转换器。

    ```
    // 1.定义转换器类实现 Converter 接口
    public class DateConverter implements Converter<String, Date> {

        @Override
        public Date convert(String source) {
            // 1.将日期的字符串转成日期对象，返回
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = format.parse(source);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }
    }

    ```

    ```
    // 2. 在配置文件中声明转换器
    <!--声明转换器-->
    <bean id="conversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">
        <property name="converters">
            <list>
                <bean class="yimu.converter.DateConverter"></bean>
            </list>
        </property>
    </bean>
    ```

    ```
    // 3. 在 <annotation-driven/> MVC 的注解驱动中引用
     <mvc:annotation-driven conversion-service="converterService"/>
    ```
## 10、获得 Servlet 相关的 API

&emsp;&emsp;SpringMVC 支持使用原始的 ServletAPI 对象作为控制器方法的参数进行注入，常用的对象如下：
* HttpServletRequest
* HttpServletResponse
* HttpSession

    ```
    // 7.获得Servlet相关API
    @RequestMapping("/quick9")
    @ResponseBody
    public void quickMethod9(HttpServletRequest req, HttpServletResponse response, HttpSession session){
        System.out.println(req);
        System.out.println(response);
        System.out.println(session);
    }
    ```

    ![获取 HttpServlet 相关的API](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%8E%B7%E5%8F%96%20HttpServlet%20%E7%9B%B8%E5%85%B3%E7%9A%84API.png)

## 11、获取请求头

1. @RequestHeader

    &emsp;使用 @RequestHeader 可以获得请求头信息，相当于 web 阶段的 request.getHeader(name)。

    &emsp;@RequestHeader 注解的属性如下：

      * **value**: 请求头的名称
      * **required**: 是否必须携带此请求头

        ```
        // 8.获取请求头: @RequestHeader
        @RequestMapping("/quick10")
        @ResponseBody
        public void quickMethod10(@RequestHeader(value = "User-Agent",required = false) String User_Agent){
            System.out.println(User_Agent);
        }
        ```

        ![@RequestHeader获取请求头](https://raw.githubusercontent.com/yimu-0412/image/master/image/%40RequestHeader%E8%8E%B7%E5%8F%96%E8%AF%B7%E6%B1%82%E5%A4%B4.png)

2. @CookieValue

    &emsp;使用 @CookieVlue 可以获得指定 Cookie 的值

    &emsp;@CookieValue 注解的属性如下

    * value： 指定 cookie的 名称
    * required：是否必须携带此 cookie

    ```
    // 8.获取请求头: @CookieValue(JSESSIONID)
    @RequestMapping("/quick11")
    @ResponseBody
    public void quickMethod11(@CookieValue(value = "JSESSIONID",required = false) String jsessionid){
        System.out.println(jsessionid);
    }
    ```

    ![获取cookie的值](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%8E%B7%E5%8F%96cookie%E7%9A%84%E5%80%BC.png)

## 12、文件上传

1. 文件上传客户端三要素
   1. 表单项 type = “file”；
   2. 表单的提交方式是 post；
   3. 表单的 enctype 属性是多部分表单形式，即 enctype=“multipart/from-data”

2. 文件上传原理

    * 当 form 表单修改为多部分表单时，request.getParameter() 将失效；
    * enctype=“appication/x-www-form-urlencoded”时，form 表单的正文内容是：key=value&key=value&key=value
    * 当 form 表单的enctype取值为 Multipart/form-data时，请求的内容就变成多部分形式：

        ![form表单文件上传](https://raw.githubusercontent.com/yimu-0412/image/master/image/form%E8%A1%A8%E5%8D%95%E6%96%87%E4%BB%B6%E4%B8%8A%E4%BC%A0.png)

3. 单文件上传步骤

    1. 导入 **fileupload** 和 **io** 坐标

        ```
          <!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>1.4</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.11.0</version>
        </dependency>
        ```

    2. 配置文件上传解析器

        ```
        <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">

            <!--上传文件的编码类型-->
            <property name="defaultEncoding" value="UTF-8"></property>
            <!--上传文件的总大小-->

            <property name="maxUploadSize" value="5242800"></property>

            <!--上传单个文件的大小-->
            <property name="maxUploadSizePerFile" value="5242800"></property>
        
        </bean>
        ```

    3. 编写文件上传代码

        ```
        <form action="${pageContext.request.contextPath}/user/uploadFile1" method="post" enctype="multipart/form-data">
            名称 <input type="text" name="name">
            文件 <input type="file" name="uploadFile">
            <input type="submit" name="提交">
        </form>
        ```

        ```
        // 1. 单文件上传
        @RequestMapping("/uploadFile1")
        @ResponseBody
        public void uploadFile1(String name, MultipartFile uploadFile) throws IOException {
            System.out.println(name);
            System.out.println(uploadFile);
            // 获取文件名称
            String originalFilename = uploadFile.getOriginalFilename();
            // 保存文件
            uploadFile.transferTo(new File("c:\\upload\\" + originalFilename));
        }
        ```
4. 多文件上传

&emsp;&emsp;多文件上传，只需要将页面修改为多个文件上传项，将方法参数``MultipartFile`` 类型修改为 ``MultipartFile[]`` 即可。

```
    form action="${pageContext.request.contextPath}/user/uploadFile2" method="post" enctype="multipart/form-data">
        名称 <input type="text" name="name"><br>
        文件 <input type="file" name="uploadFile1">
        <br>
        名称 <input type="text" name="name"><br>
        文件 <input type="file" name="uploadFile2">
        <br>
        <input type="submit" name="提交">
    </form>
```

```
    // 2.多文件上传(相同文件名)：for循环
    @RequestMapping("/uploadFile3")
    @ResponseBody
    public void uploadFile3(String[] name,MultipartFile[] uploadFile) throws IOException {

        for (MultipartFile multipartFile : uploadFile) {
            System.out.println(multipartFile);
            // 1.获取上传文件名
            String originalFilename = multipartFile.getOriginalFilename();
            // 2.保存文件
            multipartFile.transferTo(new File("c:\\upload\\" + originalFilename));
        }
    }
```

![多文件上传](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%A4%9A%E6%96%87%E4%BB%B6%E4%B8%8A%E4%BC%A0.png)