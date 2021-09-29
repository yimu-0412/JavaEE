import static java.lang.Thread.sleep;

/**
 * @author wangyimu
 * @Program 1.通过继承 Thread，重写 run
 * @create 2021-09-29-22:15
 */

class MyThread1 extends Thread {

    @Override
    public void run() {
        while(true){
            System.out.println("Hello Thread!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadDemo1 {
    public static void main(String[] args) {
        Thread t = new MyThread1();
        // t.start();
        t.run();
        while(true){
            System.out.println("Hello main!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
