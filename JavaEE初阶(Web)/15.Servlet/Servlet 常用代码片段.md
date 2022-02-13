## 1、pom.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>day_05</artifactId>
    <version>1.0-SNAPSHOT</version>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>7</source>
                    <target>7</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <!-- 指定属性信息 -->
    <properties>
        <encoding>UTF-8</encoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>


    <dependencies>
        <!-- 加入 servlet 依赖 -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <!-- servlet 版本和 tomcat 版本有对应关系，切记 -->
            <version>3.1.0</version>
            <!-- 这个意思是我们只在开发阶段需要这个依赖，部署到 tomcat 上时就不需要了 -->
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>org.thymeleaf</groupId>
            <artifactId>thymeleaf</artifactId>
            <version>3.0.15.RELEASE</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.13.1</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.27</version>
        </dependency>
    </dependencies>

    <!-- 打包方式是 war 包，一种用于 web 应用的包，原理类似 jar 包 -->
    <packaging>war</packaging>

    <build>
    <!-- 指定最终 war 包的名称 -->
    <finalName>test</finalName>
    </build>

</project>
```

## 2、web.xml

```
<!DOCTYPE web-app PUBLIC
    "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
    "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
    <display-name>Archetype Created Web Application</display-name>
</web-app>
```

## 3、hello world

```
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        resp.getWriter().write("hello");
    }
}
```

## 4、读取请求报头

```
@WebServlet("/getParameter")
public class GetParameter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        String contentType = req.getHeader("Content-Type");
        // 或者使用
        String contentType = req.getContentType();
    }
}
```

## 5、读取 GET 请求的 query string

```
@WebServlet("/getParameter")
public class GetParameterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        String userId = req.getParameter("userId");
        String classId = req.getParameter("classId");
        resp.getWriter().write("userId: " + userId +
                "; classId: " + classId );
    }
}
```
## 6、读取 POST 请求的 body(html格式)

```
@WebServlet("/postParameter")
public class PostParameter extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");

        req.setCharacterEncoding("utf-8");
        String userId = req.getParameter("userId");
        String classId = req.getParameter("classId");
        resp.getWriter().write("userId: " + userId + ", " + "classId: " +
        classId);
    }
}
```
## 7、读取 POST 请求的 body(json格式)

```
// 通过这个类来表示解析后的结果
class JsonData{
    public int userId;
    public int classId;
}

@WebServlet("/postParameterJson2")
public class PostParameterJsonServlet2 extends HttpServlet {
    @Override
    protected void  doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先将整个 body 读取
        String body = readBody(req);
        // 使用 Jackson 进行解析
        ObjectMapper objectMapper = new ObjectMapper();
        JsonData jsonData = objectMapper.readValue(body, JsonData.class);
        resp.getWriter().write(String.format("userId: %d;classId: %d",
                jsonData.userId,jsonData.classId));
    }

    private String readBody(HttpServletRequest req) throws IOException {
        // 读取 body 需要根据 req getInputStream 得到一个流对象，从这个流对象中进行读取
        InputStream inputStream = req.getInputStream();
        // 通过 contentLength 拿到请求中的 body 的字节数
        int contentLength = req.getContentLength();
        byte[] buffer = new byte[contentLength];
        inputStream.read(buffer);
        return new String(buffer,"utf-8");
    }
}
```
## 8、设置状态码

```

@WebServlet("/status")
public class StatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        // 让用户传入一个请求
        // 请求在 query String 带一个参数，就表示响应的状态码
        // 然后根据用户的输入，返回不同的状态码的响应
        String statusString = req.getParameter("status");
        if(statusString == null || statusString == ""){
            resp.getWriter().write("当前的请求参数 status  缺失");
            return;
        }
        resp.setStatus(Integer.parseInt(statusString));
        resp.getWriter().write("status： " + statusString);

    }
}
```

## 9、设置响应报头

```
@WebServlet("/autoRefreshServlet")
public class AutoRefreshServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        
        resp.setHeader("Refresh", "1");
    }
} 
```

## 10、重定向

```
@WebServlet("/redirectServlet")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        resp.sendRedirect("http://www.sogou.com");
    }
}
```

## 11、登陆页面

```
<form action="login" method="POST">
    <input type="text" name="username">
    <input type="password" name="password">
    <input type="submit" value="提交">
</form>
```

## 12、创建新 Session

```
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        session.setAttribute("username", "admin");
        session.setAttribute("loginCount", "0");
    }
}
```

## 13、获取已有 Session

```
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("test/html;charset=utf-8");
        // 1. 判断当前用户是否登录
        HttpSession session = req.getSession(false);
        if(session == null){
            // 用户没有登录重定向到 login.html
            resp.sendRedirect("login.html");
            return;
        }
        // 2. 如果已经登录，则从 Session 中取出访问次数数据
        String username = (String) session.getAttribute("username");
        String countString = (String) session.getAttribute("loginCount");
        int loginCount = Integer.parseInt(countString);
        loginCount += 1;
        session.setAttribute("loginCount",loginCount + "");

        // 3. 展示到页面上
        StringBuilder html = new StringBuilder();
        html.append(String.format("<div>用户名： %s</div>",username));
        html.append(String.format("<div>loginCount: %d</div>",loginCount));
        resp.getWriter().write(html.toString());

    }
}
```

## 14、上传文件

```
@MultipartConfig
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = getServletContext().getRealPath("/image");
        Part part = req.getPart("myImage");

        System.out.println(part.getSubmittedFileName());
        System.out.println(part.getContentType());
        System.out.println(part.getSize());

        part.write(path +"/" + part.getSubmittedFileName());
        resp.getWriter().write("upload ok!");

    }
}
```

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>上传文件</title>
</head>
<body>
    <form action="upload" method="post" enctype="multipart/form-data">
        <input type="file" name="Myimage">
        <input type="submit" value="提交图片">
    </form>
</body>
</html>
```