import java.util.concurrent.Semaphore;

/**
 * @author wangyimu
 * @Program 信号量的练习
 * @create 2021-10-15-21:05
 */
public class ThreadDemo29 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(4);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // 先尝试申请资源
                    System.out.println("准备申请资源:");
                    semaphore.acquire();
                    // 申请到了之后,sleep 1000 ms
                    System.out.println("申请到了资源:");
                    Thread.sleep(1000);
                    // 在释放资源
                    semaphore.release();
                    System.out.println("释放资源完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // 创建20个线程,让20个线程分别去申请资源
        for(int i = 0;i < 20; i ++){
            Thread t = new Thread(runnable);
            t.start();
        }
    }
}
