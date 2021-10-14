/**
 * @author wangyimu
 * @Program 创建一个线程,计算1~1000的和
 * @create 2021-10-14-22:11
 */
public class ThreadDemo25 {
    static class Result{
        public int sum;
        public Object locker = new Object();
    }
    public static void main(String[] args) throws InterruptedException {
        Result result = new Result();
        Thread t = new Thread(){

            @Override
            public void run() {
                int sum = 0;
                for(int i = 1; i <= 1000;i ++){
                    sum += i;
                }
                result.sum = sum;
                synchronized (result.locker) {
                    result.locker.notify();
                }
            }
        };
        t.start();

        // 此处我们期望,这个线程的计算结果能够被获取到
        //  为了解决这个问题.我们需要引入一个辅助的类
        // 当代码写成这个样子的时候,发现在代码中,是无法得到 sum 的值的
        // 主要是因为当前 t 线程和主线程之间是并发的关系
        // 执行的先后顺序不能确定
        // 解决方案是,让 main 这个线程先等待(wait),t 线程计算完毕之后,然后唤醒 main 线程即可
        synchronized (result.locker) {
            while(result.sum == 0){
                result.locker.wait();
            }
        }
        System.out.println(result.sum);
    }
}
