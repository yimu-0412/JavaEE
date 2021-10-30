import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author wangyimu
 * @Program 实现线程池
 * @create 2021-10-30-16:58
 */
public class ThreadExer4 {
    static class Worker extends Thread{
        private BlockingQueue<Runnable> queue = null;
        public Worker(BlockingQueue queue){
            this.queue = queue;
        }

        // 工作线程的具体安排
        // 需要从阻塞队列取出任务
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    Runnable command = queue.take();
                    command.run();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadPool{
        // 包含一个阻塞队列,用来组织任务
        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>();

        // List 用来存放当前的工作线程
        List<Thread> workers = new ArrayList<>();

        private static final int MAX_WORKERS_COUNT = 10;

        // 通过这个方法,将线程加入到线程池中
        // sumbit 不仅可以把把线程加入到阻塞队列中,同时还可以负责创建线程
        public void sumbit(Runnable command) throws InterruptedException {
            if(workers.size() < MAX_WORKERS_COUNT){
                // 如果当前工作线程的数目小于线程数目的上限,就创建出新的线程
                // 工作线程见专门来创建一个类来完成
                // worker 内部要能够取到队列的内容,就需要把这个队列实例通过 worker 的构造方法,传过去
                Worker worker = new Worker(queue);
                worker.start();
                workers.add(worker);
            }
            // 将任务添加到任务列中
            queue.put(command);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool pool = new ThreadPool();
        pool.sumbit(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <= 20; i ++){
                    System.out.println(i + ": hello");
                }
            }
        });
        Thread.sleep(5000);
    }
}
