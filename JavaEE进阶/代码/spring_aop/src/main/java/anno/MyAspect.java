package anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author
 * @Program
 * @create 2022-03-11-12:19
 */

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

    @After("MyAspect.pointcut()")
    public void after(){
        System.out.println("最终增强！");
    }

    // 定义切点表达式
    @Pointcut("execution(* anno.*.*(..))")
    public void pointcut(){}

}
