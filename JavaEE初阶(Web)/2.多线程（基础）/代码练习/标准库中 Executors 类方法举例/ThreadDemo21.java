import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangyimu
 * @Program 标准库中 Executors 类方法举例
 * @create 2021-10-12-22:12
 */
public class ThreadDemo21 {
    public static void main(String[] args) {
        // 使用以下标准库中的线程池
        // 先创建出一个线程池的实例
        ExecutorService service = Executors.newFixedThreadPool(10);
        // 在这个实例中加入一些任务
        for (int i = 0; i < 10; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hello");
                }
            });
        }
    }
}
