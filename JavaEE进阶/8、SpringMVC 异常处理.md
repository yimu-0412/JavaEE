## 1、异常处理的思路

&emsp;&emsp;系统中异常包括两类：**预期异常**和**运行时异常（RuntimeException）**，前者通过捕获异常从而获取异常信息，后者主要是通过规范代码开发、测试等手段减少运行时异常的发生。

&emsp;&emsp;系统中的 Dao、Service、Controller 出现都通过 throws Exception 向上抛出，最后由 SpringMVC 前端控制器交由异常处理器进行异常处理，如下图：

&emsp;&emsp;![SpringMVC 异常处理思想](https://raw.githubusercontent.com/yimu-0412/image/master/image/SpringMVC%20%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86%E6%80%9D%E6%83%B3.png)

## 2、异常处理的两种方式

1. 使用 SpringMVC 提供的简单的异常处理器 SimpleMappingExceptionResolver。
2. 实现 Spring 的异常处理器接口 HandlerExceptionResolver 自定义自己的异常处理器。
## 3、简单异常处理器 SlimpleMappingExceptionResolver

&emsp;&emsp;SpringMVC 已经定义好了该类型转换器，在使用时可以根据项目的具体情况进行相应异常与视图的映射配置。

```
    <!--配置异常处理器-->
    <bean id="exceptionResolver" class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
        <property name="defaultErrorView" value="error"/>  默认错误视图
        <property name="exceptionMappings"> 
            <map>
                <entry key="java.lang.ClassCastException" value="error1"/> 异异常类型 + 错误视图
                <entry key="java.lang.ArithmeticException" value="error2"/>
                <entry key="java.io.FileNotFoundException" value="error3"/>
                <entry key="java.lang.NullPointerException" value="error4"/>
                <entry key="exception.MyException" value="error5"/>
            </map>
        </property>
    </bean>
```

## 4、自定义异常处理步骤

1. 创建异常处理器实现 HandlerExceptionResolver

    ```
    public class MyExceptionResolver implements HandlerExceptionResolver {

        /*
        * 参数 Exception 异常对象
        * 返回 ModelAndView 跳转到错误视图信息
        * */
        @Override
        public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
            if(ex instanceof MyException){
                modelAndView.addObject("info","自定义异常");
            }else if(ex instanceof ClassCastException){
                modelAndView.addObject("info","类型转换异常");
            }else if(ex instanceof ArithmeticException){
                modelAndView.addObject("info","抛出0异常");
            }else if(ex instanceof FileNotFoundException) {
                modelAndView.addObject("info", "文件未找到异常");
            }else if(ex instanceof NullPointerException){
                modelAndView.addObject("info","空指针异常");
            }

            modelAndView.setViewName("error");
            return modelAndView;
        }
    }
    ```
2. 配置异常处理器

    ```
    <!--自定义异常处理-->
    <bean class="resolver.MyExceptionResolver"></bean>
    ```
3. 编写异常页面

    ```
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
        <head>
            <title>Title</title>
        </head>
        <body>
            <h2>${info}</h2>
        </body>
    </html>
    ```
4. 测试异常跳转

    ```
    @Controller
    public class ExceptionCntroller {

        @Autowired
        private DemoExceptionImpl demoException;

        @RequestMapping("/show")
        public void show(@RequestParam(value = "name" ,required = true) String name) throws Exception{
            System.out.println("show running ····");
            //demoException.show1();
            //demoException.show2();
            demoException.show3();
            //demoException.show4();
            //demoException.show5();
        }
    }
    ```

    ```
    public class DemoExceptionImpl implements DemoException{

        @Override
        public void show1() {
            System.out.println("抛出类型转换异常···");
            Object str = "zhangsan";
            Integer n = (Integer)str;
        }

        @Override
        public void show2() {
            System.out.println("抛出0异常");
            int i = 1/0;
        }

        @Override
        public void show3() throws FileNotFoundException {
            System.out.println("文件找不到");
            InputStream in = new FileInputStream("c:xxx/xxx/xxx");
        }

        @Override
        public void show4() {
            System.out.println("空指针异常");
            String str = null;
            System.out.println(str.length());
        }

        @Override
        public void show5() throws MyException {
            System.out.println("自定义异常");
            throw new MyException();
        }
    }
    ```

## 5、知识要点

### 1、异常处理方式

1. 配置简单异常处理器 SimpleMappingExceptionResolver
2. 自定义异常处理器

### 2、自定义异常处理步骤

1. 创建异常处理器实现 HandlerExceptionResolver
2. 配置异常处理器
3. 编写异常页面
4. 测试异常跳转