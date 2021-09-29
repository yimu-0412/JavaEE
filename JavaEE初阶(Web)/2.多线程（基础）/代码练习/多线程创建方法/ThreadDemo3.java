

/**
 * @author wangyimu
 * @Program 3.继承 Thread ，重写 run ，使用匿名内部类的方式
 * @create 2021-09-29-22:55
 */

public class ThreadDemo3 {
    public static void main(String[] args) {
        // 这个语法是匿名内部类
        // 相当于创建了一个匿名的类，这个类是继承了 Thread
        // 此处 new 的实例，其实是 new 了这个新的子类的实例，
        Thread t = new Thread(){
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
        };
        t.start();
    }
}
