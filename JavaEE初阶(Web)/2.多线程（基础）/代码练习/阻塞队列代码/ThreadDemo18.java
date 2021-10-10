import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author wangyimu
 * @Program 阻塞队列代码
 * @create 2021-10-10-20:31
 */
public class ThreadDemo18 {
    static class BlockingQueue{
        // 1000 相当于队列的最大容量,此处不考虑扩容的问题
        private int[] items = new int[1000];
        private int head = 0;
        private int tail = 0;
        private int size = 0;
        private Object locker = new Object();

        // put 用来入队列
        public void put(int item) throws InterruptedException {
            synchronized (locker) {
                // 入队列,就是把新元素放到 tail 位置上
                // 此处的条件最好写作 while 而不是 if
                // 如果有多个线程进行阻塞等待的同时,万一唤醒了多个线程,
                // 就有可能出现,第一个线程放入元素之后,第二个线程想放,就又满了的情况
                // 虽然当前的 take 的代码中使用的是 notify ,一次只唤醒一个等待的线程,用 if 也没有错
                // 但是,使用 while 还能更稳健一些'
                // 使用 while 的意思就是,保证 wait 被唤醒的同时能够再确认一次队列确实不满
                while(size == items.length){
                    // 队列已满
                    // 对于阻塞队列来说就要阻塞
                    // TOOD
                    locker.wait();
                }
                items[tail]  = item;
                tail ++;

                // 如果到达末尾,就回到起始位置
                if(tail >= items.length){
                    tail = 0;
                }

                // tail %= items.length;
                size ++;
                locker.notify();
            }
        }
        // take 用来出队列
        public int take() throws InterruptedException {
            int ret = 0;
            synchronized (locker) {
                while (size == 0){
                    // 对于阻塞队列来说,如果队列为空,再尝试取元素,就要阻塞
                    locker.wait();
                }
                ret = items[head];
                head ++;
                if(head >= items.length){
                    head = 0;
                }
                size --;
                // 此处的 notify 用来唤醒 put 中的 wait
                locker.notify();
            }
            return ret;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new BlockingQueue();
        /*queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.put(4);

        int elem = queue.take();
        System.out.println(elem);
        elem = queue.take();
        System.out.println(elem);
        elem = queue.take();
        System.out.println(elem);
        elem = queue.take();
        System.out.println(elem);*/
// 创建一个线程作为消费者
        Thread customer = new Thread(){
            @Override
            public void run() {
                while(true){
                    // 取队首元素
                    try {
                        Thread.sleep(1000);
                        Integer value = queue.take();
                        System.out.println("消费元素:" + value);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        customer.start();

        // 创建一个线程作为生产者
        Thread producer = new Thread(){

            @Override
            public void run() {
                for(int i = 1; i < 10000; i ++){
                    System.out.println("生产元素:" + i);
                    try {
                        queue.put(i);
                        // Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        producer.start();

        try {
            customer.join();
            producer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
