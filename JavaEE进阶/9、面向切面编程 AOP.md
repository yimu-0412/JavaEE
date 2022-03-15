## 一、Spring 的 AOP 简介
### 1、什么是 AOP

&emsp;&emsp;AOP 为 Aspect Oriented Programming 的缩写，意思为**面向切面编程**，是通过**预编译**方式和**运行期动态代理**实现程序功能的统一维护的一种技术。

&emsp;&emsp;AOP 是 OOP 的延续，是软件开发中的一个热点，也是 Spring 框架中的一个重要内容，是函数式编程的一种衍生模型。利用 AOP 可以对业务逻辑的各个部分进行隔离，从而使业务逻辑各个部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

### 2、AOP 的作用及其优势

1. 作用：在程序运行期间，在不修改源码的情况下对方法进行功能增强
2. 优势：减少代码重复，提高开发效率，并且便于维护

### 3、AOP 的底层实现
### 4、AOP 的动态代理技术
### 5、JDK 的动态代理

1. 目标类接口

    ```
    public interface TargetInterface {
        public void save();
    }
    ```
2. 目标类

    ```
    public class Target implements TargetInterface{
        @Override
        public void save() {
            System.out.println("save running ···");
        }
    }
    ```
3. 动态代理代码

    ```
    // 目标对象
    final Target target = new Target();

    // 增强对象
    final Advice advice = new Advice();

    // 返回值 动态生成的代理对象
    TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(
            // 目标对象的类加载器
            target.getClass().getClassLoader(),
            // 目标对象相同的接口字节码对象数组
            target.getClass().getInterfaces(), new InvocationHandler() {
                // 调用的代理对象的任何方法 实质执行的都是 invoke 方法
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // 执行目标方法
                    // 前置增强
                    advice.before();
                    Object invoke = method.invoke(target, args);
                    // 后置增强
                    advice.afterReturn();
                    return invoke;
                }
            }
    );
    ```
    
4. 调用代理对象的方法测试

    ```
    // 调用代理对象的方法
    proxy.save();
    ```
    ![JDK 的动态代理](https://raw.githubusercontent.com/yimu-0412/image/master/image/JDK%20%E7%9A%84%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86.png)

### 6、cglib 的动态代理

1. 目标类

    ```
    public class Target{
        public void save(){
            System.out.println("save running ····")
        }
    }
    ```
2. 动态代理代码
   
    ``` 
    public static void main(final String[] args) {
        // 目标对象
        final Target target = new Target();
        // 代理对象
        final Advice advice = new Advice();

        // 返回值 就是动态代理生成的代理对象 基于 cglib
        // 1.创建增强器
        Enhancer enhancer = new Enhancer();
        // 2.设置父类
         enhancer.setSuperclass(Target.class);
        // 3.设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                // 执行前置
                advice.before();
                // 执行目标
                Object invoke = method.invoke(target, args);
                // 执行后置
                advice.afterReturn();

                return invoke;
            }
        });
    }
    ```
3. 代用代理对象的方法测试

    ```
    // 4.创建代理对象
        Target proxy = (Target) enhancer.create();
        proxy.save();
    ```

4. 增强代码

    ```
    public class Advice {
        public void before(){
            System.out.println("前置增强!");
        }
        public void afterReturn(){
            System.out.println("后置增强！");
        }
    }
    ```

### 7、AOP 的相关概念

&emsp;&emsp;Spring 的 AOP 实现底层就是对上面的动态代理的代码进行了封装，封装之后只需要对关注的部分代码进行编写，并通过配置的方式完成指定目标的方法增强。

&emsp;&emsp;AOP 的相关术语：

1. **Target（目标对象）**：代理的目标对象。
2. **Proxy（代理）**：一个类被 AOP 织入增强后，就产生一个结果代理类。
3. **Joinpoint（连接点）**：所谓连接点是指那些被拦截的点。在 Spring 中，这些点指的是方法，因为Spring只支持方法类型的连接点。
4. **Pointcut(切入点)**:所谓的切入点就是要对那些 Jointpoint 进行拦截的定义。
5. **Advice（通知/增强）**：所谓通知是指拦截到 Joinpoint 之后要做的事情就是通知。
6. **Aspect（切面）**：是切入点和通知（引介）的结合。
7. **Weaving（织入）**：是指把增强应用到目标对象来创建新的代理对象的过程。Spring 采用**动态代理**织入，而``AspectJ``采用编译期织入和类转载期织入。
 
### 8、AOP 开发明确的事项

1. 需要编写的内容

    * 编写核心业务代码（目标类的目标方法）
    * 编写切面类，切面类中有通知（增强功能方法）
    * 在配置文件中，配置织入关系，即将哪些通知与哪些连接点进行结合

2. AOP 技术实现的内容

&emsp;&emsp;Spring 框架监控切入点方法的执行。一旦监控到切入点方法被运行，使用代理机制，动态创建目标对象的代理对象，根据通知类别，在代理对象的相应位置，将通知对应的功能织入，完成完整的代码逻辑运行。

3. AOP 底层使用使用哪种代理方式

&emsp;&emsp;在 Spring 中，框架会根据目标类是否实现了接口来决定采用哪一种动态代理的的方式。

### 9、知识要点

* aop：面向切面编程
* aop底层实现：基于 JDK 的动态代理和基于 Cglib 的动态代理
* aop的重点概念：
  * Pointcut（切入点）：被增强的方法
  * Adive（通知/增强）：封装增强业务逻辑的方法
  * Aspect（切面）：切点 + 通知
  * Weaving（织入）：将切点和通知结合起来

* 开发明确实现：
  * 谁是切点（切点表达式配置）
  * 谁是通知（切面类中的增强方法）
  * 将切点和通知进行织入配置·

## 二、基于 XML 的 AOP 开发
### 1、快速入门
1. 导入 AOP 相关的坐标

    ```
    <!--导入spring的context坐标，context依赖aop-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.3.15</version>
    </dependency>

    <!--导入 AOP,aspectj 的织入-->
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.8.9</version>
    </dependency>
    ```
2. 创建目标接口和目标类

    ```
    public class TargetInface{
        public void save();
    }
    ```

    ```
    public class Target implements TargetInterface {
        @Override
        public void save() {
            System.out.println("save running ···");
            // int i = 1/0;
        }
    }
    ```
3. 创建切面类（内部有增强方法）

    ```
    public class MyAspect {
        public void before(){
            System.out.println("前置增强！");
        }

        public void afterReturnning(){
            System.out.println("后置增强！");
        }

        // Proceeding JoinPoint:正在执行的连接点===切点
        public Object around(ProceedingJoinPoint pjp) throws Throwable {
            System.out.println("环绕前增强！");
            // 切点方法
            Object proceed = pjp.proceed();
            System.out.println("环绕后增强！");
            return proceed;
        }

        public void afterThrowing(){
            System.out.println("异常抛出增强！");
        }

        public void after(){
            System.out.println("最终增强！");
        }
    }
    ```
4. 将目标类和切面类的对象创建权交给 spring

    ```
    <!--配置目标对象-->
    <bean id="target" class="aop.Target"></bean>

    <!--配置切面对象-->
     <bean id="mySpect" class="aop.MyAspect"></bean>
    ```
5. 在 applicationContext.xml 文件中配置织入关系

    ```
    <!--配置织入，告诉Spring框架，那些方法（切点）需要哪些增强-->
    <aop:config>
        <!--声明切面-->
        <aop:aspect ref="mySpect">
            <!--切面：切点 + 通知-->
            <aop:before method="before" pointcut="execution(* aop.*.*(..))"></aop:before>
            <aop:after-returning method="afterReturnning" pointcut="execution(* aop.*.*(..))"></aop:after-returning>-->
            <!--<aop:around method="around" pointcut="execution(* aop.*.*(..))"></aop:around>
            <aop:after-throwing method="afterThrowing" pointcut="execution(* aop.*.*(..))"></aop:after-throwing>
            <aop:after method="after" pointcut="execution(* aop.*.*(..))"></aop:after>
        </aop:aspect>
    </aop:config>
    ```
6. 测试代码

    ```
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration("classpath:appl.xml")
    public class AopTest {
        @Autowired
        private TargetInterface target;

        @Test
        public void test1(){
            target.save();
        }
    }
    ```
7. 测试结果

    ![ADP 测试](https://raw.githubusercontent.com/yimu-0412/image/master/image/ADP%20%E6%B5%8B%E8%AF%95.png)

### 2、XML 配置 AOP 详解

1. 切点表达式的写法

    表达式语法：

    ```
    execution [修饰符] 返回值类型 包名.类名.方法名(参数)
    ```
    * 访问修饰符可以省略
    * 返回值类型、包名、类名、方法名可以使用 * 号代替
    * 包名和类名之间一个点，代表当前包下的类，两个点 .. 表示当前包以及其子包下的类
    * 参数类型可以使用两个点 .. 表示任意个数，任意类型的参数列表

    例如：

    ```
    execution(public void aop.Target.method())
    execution(void aop.Target.*(..))
    execution(* aop.*.*(..))
    execution(* aop..*.*(..))
    execution(* *..*.*(..))
    ```
2. 通知的类型

    通知的配置语法：
    
    ```
    <aop:通知类型 method="切面类中方法名" pointcut="切点表达式"></aop:通知类型>
    ```
    ![aop通知类型](https://raw.githubusercontent.com/yimu-0412/image/master/image/aop%E9%80%9A%E7%9F%A5%E7%B1%BB%E5%9E%8B.png)

3. 切点表达式的抽取

    当多个增强的切点表达式相同时，可以将切点表达式进行抽取，在增强中使用 pointcut-ref 属性代替 pointcut 属性来引用抽取后的切点表达式。

    ```
    <aop:config>
        <--引用 myAspect 的 Bean 为切面对象-->
        <aop:pointcut id="mypointcut" expression="execution(* aop.*.*(..))"/>
        <aop:before method="before" pointcut-ref="myPointcut"></aop:before>
    </aop:config>
    ```
### 3、知识要点

* aop 织入配置

    ```
    <aop:config>
        <aop:aspect ref="切面类">
            <aop:before method="通知方法名称" pointcut="切点表达式"></aop:bofore>
        </aop:aspect>
    </aop:config>
    ```
* 通知的类型：前置通知、后置通知、环绕通知、异常抛出通知、最终通知
* 切点表达式的写法：

    ```
    execution([修饰符] 返回值类型包名.类名.方法名(参数))
    ```
## 三、基于注解的 AOP 开发
### 1. 快速入门

1. 创建目标接口和目标类

    ```
    public class Target implements TargetInterface {
        
        @Override
        public void save() {
            System.out.println("save running ···");
            // int i = 1/0;
        }
    }
    ```

    ```
    public interface TargetInterface {
        public void save();
    }
    ```
2. 创建切面类

    ```
    public class MyAspect {

        // 配置前置通知
        public void before(){
            System.out.println("前置增强！");
        }

        public void afterReturnning(){
            System.out.println("后置增强！");
        }

        // Proceeding JoinPoint:正在执行的连接点===切点
        public Object around(ProceedingJoinPoint pjp) throws Throwable {
            System.out.println("环绕前增强！");
            // 切点方法
            Object proceed = pjp.proceed();
            System.out.println("环绕后增强！");
            return proceed;
        }

        public void afterThrowing(){
            System.out.println("异常抛出增强！");
        }

        public void after(){
            System.out.println("最终增强！");
        }
    } 
    ```
3. 将目标类和切面类的对象创建权交给 spring

    ```
    @Component("target")
    public class Target implements TargetInterface {

        @Override
        public void save() {
            System.out.println("save running ···");
            // int i = 1/0;
        }
    }
    ```
    ```
    @Component("myAspect")
    public class MyAspect {
        ···
    }
    ```
4. 在切面类中使用注解配置织入关系

    ```
    @Component("myAspect")
    @Aspect // 标注当前类是一个切面类
    public class MyAspect {
        // 配置前置通知
        @Before("execution(* anno.*.*(..))")
        public void before(){
            System.out.println("前置增强！");
        }

        @AfterReturning("execution(* anno.*.*(..))")
        public void afterReturnning(){
            System.out.println("后置增强！");
        }

        // Proceeding JoinPoint:正在执行的连接点===切点
        @Around("execution(* anno.*.*(..))")
        public Object around(ProceedingJoinPoint pjp) throws Throwable {
            System.out.println("环绕前增强！");
            // 切点方法
            Object proceed = pjp.proceed();
            System.out.println("环绕后增强！");
            return proceed;
        }

        @AfterThrowing("execution(* anno.*.*(..))")
        public void afterThrowing(){
            System.out.println("异常抛出增强！");
        }
    }
    ```
5. 在配置文件中开启组件扫描和AOP的自动代理

    ```
     <!--开启组件扫描-->
    <context:component-scan base-package="anno"></context:component-scan>

     <!--AOP 自动代理-->
    <aop:aspectj-autoproxy/>
    ```
6. 测试代码

    ```
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration("classpath:appl-anno.xml")
    public class AnnoTest {

        @Autowired
        private TargetInterface target;

        @Test
        public void test(){
            target.save();
        }
    }
    ```
7. 测试结果

    ![基于注解AOP开发测试结果](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%9F%BA%E4%BA%8E%E6%B3%A8%E8%A7%A3AOP%E5%BC%80%E5%8F%91%E6%B5%8B%E8%AF%95%E7%BB%93%E6%9E%9C.png)
### 2、注解配置 AOP 详解

1. 注解通知的类型

    通知的配置与法：@通知注解（“切点表达式”）

    ![AOP注解通知类型](https://raw.githubusercontent.com/yimu-0412/image/master/image/AOP%E6%B3%A8%E8%A7%A3%E9%80%9A%E7%9F%A5%E7%B1%BB%E5%9E%8B.png)

2. 切点表达式的抽取

    同 xml 配置 app一样，可以将切点表达式抽取。抽取方式是切面内定义方法，在该方法使用 @Pointcut 注解定义切点表达式，然后在增强注解中进行引用。具体如下：

    ```
     @After("MyAspect.pointcut()")
    public void after(){
        System.out.println("最终增强！");
    }

    // 定义切点表达式
    @Pointcut("execution(* anno.*.*(..))")
    public void pointcut(){}
    ```
### 3、知识要点

1. 注解 AOP 开发步骤
   
   1. 使用@Aspect标注切面类
   2. 使用 @通知注解标注通知方法
   3. 在配置文件中配置aop自定代理<aop:aspect-autoproxy/>

2. 通知注解类型

     ![AOP注解通知类型](https://raw.githubusercontent.com/yimu-0412/image/master/image/AOP%E6%B3%A8%E8%A7%A3%E9%80%9A%E7%9F%A5%E7%B1%BB%E5%9E%8B.png)