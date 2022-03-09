##  1、拦截器（intterceptor）的作用

&emsp;&emsp;Spring MVC 的拦截器类似于 Servlet 开发中的过滤器 Filter，用于对处理器的预处理和后处理。

&emsp;&emsp;将拦截器按照一定的顺序联结成一条链，这条链称为**拦截器链**。在访问被拦截的方法或者字段时，拦截器链中的拦截器就会按照其定义的顺序被调用。拦截器也是 ``AOP`` 思想的具体体现。

##  2、拦截器和过滤器的区别K

![拦截器和过滤器的区别](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%8B%A6%E6%88%AA%E5%99%A8%E5%92%8C%E8%BF%87%E6%BB%A4%E5%99%A8%E7%9A%84%E5%8C%BA%E5%88%AB.png)

##  3、拦截器快速入门

1. 创建拦截器实现 HandlerInterceptor 接口

    ```
    public class MyInterceptor1 implements HandlerInterceptor {
        // 在目标方法执行之前 执行
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            System.out.println("preHandle····");
            String param = request.getParameter("param");
            if(param.equals("yes")){
                return true;
            }else{
                request.getRequestDispatcher("/error.jsp").forward(request,response);
                return false;//如果返回 true 代表放行，返回false代表不放行。
            }
        }

        // 在目标方法执行之后 视图对象返回之前执行
        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            modelAndView.addObject("name","test");
            System.out.println("postHandle····");
        }

        // 在流程执行完毕后 执行
        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            System.out.println("afterCompletion····");
        }
    }
    ```
2. 配置拦截器

    ```
    <!--配置拦截器-->
    <mvc:interceptors>
        <mvc:interceptor>
            <!--对那些资源执行拦截操作-->
            <mvc:mapping path="/**"/>
            <bean class="interceptor.MyInterceptor1"/>
        </mvc:interceptor>
    </mvc:interceptors> 
    ```
3. 测试拦截器的拦截效果

    ```
    @RequestMapping("/test")
    public ModelAndView show(){
        System.out.println("目标资源执行····");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","itcase");
        modelAndView.setViewName("index");
        return modelAndView;
    }
    ```

    ![拦截器](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%8B%A6%E6%88%AA%E5%99%A8.png)

## 4、多拦截器操作

&emsp;&emsp;在以上基础上编写一个 MyHandlerInterceptor 操作，测试执行顺序。

```
    public class MyInterceptor2 implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            System.out.println("preHandle2····");
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            System.out.println("postHandle2····");
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            System.out.println("afterCompletion2····");
        }
    }
```
&emsp;&emsp;配置拦截器
```
    <!--配置拦截器-->
    <mvc:interceptors>
        <mvc:interceptor>
            <!--对那些资源执行拦截操作-->
            <mvc:mapping path="/**"/>
            <bean class="interceptor.MyInterceptor1"/>
        </mvc:interceptor>
        <mvc:interceptor>
            <mvc:mapping path="/**"/>
            <bean class="interceptor.MyInterceptor2"/>
        </mvc:interceptor>
    </mvc:interceptors> 
```

&emsp;&emsp;![多拦截器测试效果](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%A4%9A%E6%8B%A6%E6%88%AA%E5%99%A8%E6%B5%8B%E8%AF%95%E6%95%88%E6%9E%9C.png)

## 5、拦截器方法说明

&emsp;&emsp;![拦截器方法说明](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%8B%A6%E6%88%AA%E5%99%A8%E6%96%B9%E6%B3%95%E8%AF%B4%E6%98%8E.png)

## 6、知识要点

&emsp;&emsp;自定义实现拦截器步骤：

* 创拦截器类实现 ``HandlerInterceptor`` 接口
* 配置拦截器
* 测试拦截器的拦截效果

## 7、案例-用户登录权限控制

&emsp;&emsp;需求：用户没有登录的情况下，不能对后台菜单进行访问操作，点击菜单跳转到登录页面，只有用户登录成功后才能进行后台功能的操作。

&emsp;&emsp;![用户登录权限控制](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E7%94%A8%E6%88%B7%E7%99%BB%E5%BD%95%E6%9D%83%E9%99%90%E6%8E%A7%E5%88%B6.png)

