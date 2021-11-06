package SystemTest;

import java.util.Scanner;

/**
 * @author wangyimu
 * @Program 方法一：使用Scanner实现，调用next()返回一个字符串
 * @create 2021-11-06-23:07
 */
public class PrintTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入字符串：");
        String data = scan.nextLine();
        if("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)){
            System.out.println("程序结束！");
            return;
        }
        String upper = data.toUpperCase();
        System.out.println(upper);
    }
}
