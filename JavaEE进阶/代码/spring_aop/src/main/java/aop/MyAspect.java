package aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author
 * @Program
 * @create 2022-03-11-12:19
 */
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
