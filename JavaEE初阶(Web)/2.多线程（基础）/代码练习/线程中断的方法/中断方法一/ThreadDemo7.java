/**
 * @author wangyimu
 * @Program Day_20210929
 * @create 2021-10-06-9:45
 */
public class ThreadDemo7 {
    private static boolean flag = true;
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
               while(flag){
                   System.out.println("线程运行中>>>>>");
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               System.out.println("线程结束!");
            }
        };
        t.start();

        // 主循环中也等待三秒
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 三秒钟之后,就把flag 改成 flag
        flag = false;
    }
}
