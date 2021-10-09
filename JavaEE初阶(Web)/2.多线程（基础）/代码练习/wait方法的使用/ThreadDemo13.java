/**
 * @author wangyimu
 * @Program wait 方法的使用
 * @create 2021-10-09-22:47
 */
public class ThreadDemo13 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        synchronized(o) {
            System.out.println("wait 之前");
            o.wait();
            System.out.println("wait 之后");
        }
    }
}