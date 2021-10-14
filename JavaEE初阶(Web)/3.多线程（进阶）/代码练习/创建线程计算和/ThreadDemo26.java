import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author wangyimu
 * @Program 使用 Callable 计算1~1000的和
 * @create 2021-10-14-22:29
 */
public class ThreadDemo26 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for(int i = 1; i <= 1000;i ++){
                    sum += i;
                }
                return  sum;
            }
        };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        Thread t = new Thread(futureTask);
        t.start();

        // 尝试在主线程获取结果
        // 如果 FutureTask 中的结果还没有算出来,此时就会阻塞等待
        // 一直等到最终的结果算出来之后, get 才会返回.
        Integer ret = futureTask.get();
        System.out.println(ret);
    }
}
