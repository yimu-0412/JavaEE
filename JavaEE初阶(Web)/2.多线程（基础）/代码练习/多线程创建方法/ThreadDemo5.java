/**
 * @author wangyimu
 * @Program 5.使用lambda表达式创建线程
 * @create 2021-09-29-23:10
 */
public class ThreadDemo5 {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            while(true){
                System.out.println("hello Thread!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }) ;
        t.start();
    }
}
