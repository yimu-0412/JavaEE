/**
 * @author wangyimu
 * @Program 2.实现 Runnable 接口，重写 run
 * @create 2021-09-29-22:32
 */
class MyRunnable implements Runnable{
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

public class ThreadDemo2 {
    public static void main(String[] args) {
        Thread T = new Thread(new MyRunnable());
        T.start();
    }
}

