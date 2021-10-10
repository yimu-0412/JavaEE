

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author wangyimu
 * @Program 简单的生产者消费者模型
 * @create 2021-10-10-20:15
 */
public class ThreadDemo17 {
    public static void main(String[] args) {
        // 使用一个 BlockingQueue 作为交易场所
        BlockingDeque<Integer> queue = new LinkedBlockingDeque<>();
        // 创建一个线程作为消费者
        Thread customer = new Thread(){
            @Override
            public void run() {
                while(true){
                    // 取队首元素
                    try {
                        Integer value = queue.take();
                        System.out.println("消费者元素:" + value);
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
                    System.out.println("生产了元素:" + i);
                    try {
                        queue.put(i);
                        Thread.sleep(1000);
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
