public class ThreadDemo12 {
    static class Counter{
        public int count = 0;

//        synchronized public void increase(){
//            count ++;
//        }

        public void increase(){
            synchronized (this){
                count ++;
            }
        }
    }
    static  Counter counter = new Counter();
    public static void main(String[] args) {
        // 此处创建两个线程,分别针对 count 自增5万次
        Thread t1 = new Thread(){
            @Override
            public void run() {
                for(int i = 0;i < 50000; i ++){
                    counter.increase();
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                for(int i = 0;i < 50000; i ++){
                    counter.increase();
                }
            }
        };
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.count);
    }
}