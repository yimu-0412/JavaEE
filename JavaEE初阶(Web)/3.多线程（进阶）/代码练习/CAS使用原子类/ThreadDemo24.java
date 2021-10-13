import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangyimu
 * @Program ThreadExer
 * @create 2021-10-13-22:56
 */
public class ThreadDemo24 {
    // 使用原子类
    public static void main(String[] args) {
        AtomicInteger nums = new AtomicInteger(10);

        // 这个操作就相当于 nums ++
        // 这样的方法
        nums.getAndIncrement();
        System.out.println(nums);
        // 这个操作就相当于 ++ nums
        nums.incrementAndGet();
        System.out.println(nums);

    }
}
