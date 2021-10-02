public class ThreadDemo6 {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    // 打印当前线程的名字
                    // Thread.currentThread() 这个静态方法,来获取当前线程实例
                    // 哪个线程调用的这个方法.就能获取到对应的实例
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"mythread");

        t.start();
        // 打印线程的属性
        System.out.println("id: " + t.getId());
        System.out.println("name: " + t.getName());
        System.out.println("state: " + t.getState());
        System.out.println("priority: " + t.getPriority());
        System.out.println("isDaemon: " + t.isDaemon() );
        System.out.println("isInterupted: " + t.isInterrupted());
        System.out.println("isLive: " + t.isAlive());


    }
}
