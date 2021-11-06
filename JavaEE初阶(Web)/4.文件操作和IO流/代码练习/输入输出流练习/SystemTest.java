package SystemTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author wangyimu
 * @Program
 *  1.3练习：从键盘输入字符串，要求将读取到的整行字符串转成大写输出。然后继续进行输入操作，
 *          直至当输入“e”或者“exit”时，退出程序。
 *   方法一：使用Scanner实现，调用next()返回一个字符串
 *   方法二：使用System.in实现。System.in  --->  转换流 ---> BufferedReader的readLine()
 * @create 2021-11-06-22:51
 */
public class SystemTest {
    public static void main(String[] args) {
        try (InputStreamReader isr = new InputStreamReader(System.in);
             BufferedReader br = new BufferedReader(isr)) {
            while(true){
                System.out.println("请输入字符串:");
                String data = br.readLine();
                if("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)){
                    System.out.println("程序结束！");
                    break;
                }
                String upper = data.toUpperCase();
                System.out.println(upper);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
