package proxy.jdk;

/**
 * @author
 * @Program
 * @create 2022-03-11-9:06
 */
public class Advice {
    public void before(){
        System.out.println("前置增强!");
    }
    public void afterReturn(){
        System.out.println("后置增强！");
    }
}
