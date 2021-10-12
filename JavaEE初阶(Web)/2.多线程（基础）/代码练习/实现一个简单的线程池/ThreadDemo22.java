

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author wangyimu
 * @Program 实现一个简单的线程池
 * @create 2021-10-12-23:04
 */
public class ThreadDemo22 {
    static class Worker extends Thread{
        private BlockingQueue<Runnable> queue = null;

        public Worker(BlockingQueue<Runnable> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            // 工作线程具体的逻辑
            // 需要从zuse队列中取具体的任务
            while(true){
                try {
                    Runnable command = queue.take();
                    // 通过这个 run 执行这个具体的任务
                    command.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ThreadPool{
        // 包含一个阻塞队列,用来组织任务
        private BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>();

        // list 用来存放当前的工作线程.
        private List<Thread> workers = new ArrayList<>();

        private static final int MAX_WORKER_COUNT = 10;

        // 通过这个方法,将任务加入到线程池中
        // submit 不仅可以把任务放到阻塞队列中,同时还可以负责创建线程
        public void submit(Runnable command) throws InterruptedException {
            if(workers.size() < MAX_WORKER_COUNT){
                // 如果当前工作线程的数量不足线程数目上限,就创建出新的线程
                // 工作线程就专门搞一个类来完成
                // worker 内部要能够取到队列的内容,就需要把这个队列实例通过 worker 的构造方法,传过去
                Worker worker = new Worker(queue);
                worker.start();
                workers.add(worker);
            }
            queue.put(command);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool pool = new ThreadPool();
        for (int i = 0;i < 10;i ++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hello");
                }
            });
        }
    }

}
