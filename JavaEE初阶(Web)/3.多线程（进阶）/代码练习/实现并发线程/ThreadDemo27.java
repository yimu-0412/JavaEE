import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangyimu
 * @Program ReentrantLock类实现并发线程
 * @create 2021-10-14-22:48
 */
public class ThreadDemo27 {
    static class Counter{
        public int count;
        public ReentrantLock locker = new ReentrantLock();
        public void increse() {
            locker.lock(); // 加锁
            count ++;
            locker.unlock(); // 解锁
        }
    }
    public static void main(String[] args) {
        Counter c = new Counter();
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
