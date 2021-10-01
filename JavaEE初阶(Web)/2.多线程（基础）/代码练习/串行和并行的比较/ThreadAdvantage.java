// 使用 System.nanoTime() 可以记录当前系统的 纳秒 级时间戳.
// serial() 串行的完成一系列运算. concurrency()使用两个线程并行的完成同样的运算.
public class ThreadAdvantage {
    private static final long count = 10_0000_0000;

    private static void concurrency(){
        long begin = System.nanoTime();

        // 利用一个线程计算 a 的值
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for(long i = 1; i < count ; i ++){
                    a--;
                }
            }
        });
        thread.start();
        // 主线程内计算 b 的值
        int b = 0;
        for(long i = 1; i < count ; i ++){
            b--;
        }

        // 等待 thread 线程运行结束
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.nanoTime();
        double ms = (end - begin)* 1.0 / 1000 / 1000;
        System.out.println("并发:" + ms);
    }

    private static void serial(){
        // 全部在主线程内计算 a、b 的值
        long begin = System.nanoTime();

        int a = 0;
        for(long i = 1; i < count ; i ++){
            a--;
        }
        int b = 0;
        for(long i = 1; i < count ; i ++){
            b--;
        }

        long end = System.nanoTime();
        double ms = (end - begin) * 1.0 / 1000 / 1000;
        System.out.println("串行:" + ms);
    }

    public static void main(String[] args) {
        // 使用并发方式
        concurrency();  // 并发:653.6996
        // 使用串行方式
        serial(); // 串行:1243.4612
    }
}