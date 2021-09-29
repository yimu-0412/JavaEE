/**
 * @author wangyimu
 * @Program 4.实现Runnable，重写run，使用匿名内部类
 * @create 2021-09-29-23:06
 */
public class ThreadDemo4 {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println("hello Thread!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }
}
