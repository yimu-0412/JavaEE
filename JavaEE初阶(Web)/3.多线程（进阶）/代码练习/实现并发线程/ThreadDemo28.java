import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangyimu
 * @Program 使用原子类实现并发线程
 * @create 2021-10-14-23:06
 */
public class ThreadDemo28 {
    static class Counter{
        public AtomicInteger count = new AtomicInteger(0);

        public void increse() {
            count.getAndIncrement();
        }
    }
    public static void main(String[] args) {
        ThreadDemo27.Counter c = new ThreadDemo27.Counter();
        Thread t1 = new Thread(){
            @Override
            public void run() {
                for(int i = 1;i <= 50000; i ++){
                    c.increse();
                }

            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                for(int i = 1;i <= 50000; i ++){
                    c.increse();
                }

            }
        };

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(c.count);

    }
}
