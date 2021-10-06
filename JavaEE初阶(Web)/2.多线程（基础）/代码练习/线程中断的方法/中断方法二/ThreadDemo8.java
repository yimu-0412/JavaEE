/**
 * @author wangyimu
 * @Program Day_20210929
 * @create 2021-10-06-9:54
 */
public class ThreadDemo8 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            @Override
            public void run() {
                // 默认情况 isInterrupted 值为 false
                while( !Thread.currentThread().isInterrupted()){
                    System.out.println("线程运行中>>>>>");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                         e.printStackTrace();
                        // 在这里再加个 break 就可以保证循环能结束了
                       // break;
                    }
                }
                System.out.println("线程结束!");
            }
        };
        t.start();
        // 在主线程中,通过 t.interrupt() 方法来设置这个标记位
        Thread.sleep(3000);
        // 这个操作就是把 Thread.currentThread().isInterrupted() 给设置成 true
        t.interrupt();
    }
}
