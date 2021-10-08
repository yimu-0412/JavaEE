public class ThreadDemo10 {

    public static void main(String[] args) {
        final Object object = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                   while(true){
                       try {
                          // Thread.sleep(1000);
                          object.wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
                }
            }
        },"t1");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    System.out.println("hehe");
                }
            }
        },"t2");
        t2.start();
    }
}