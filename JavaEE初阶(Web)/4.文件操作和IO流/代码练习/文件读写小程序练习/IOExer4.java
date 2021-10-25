package IOExer;

import java.io.*;
import java.util.Scanner;

/**
 * @author wangyimu
 * @Program 利用 PrintWriter 写入文件练习
 * @create 2021-10-21-20:32
 */
public class IOExer4 {
    public static void main(String[] args) {
        try (OutputStream os = new FileOutputStream("output.txt")) {
            try (OutputStreamWriter osWriter = new OutputStreamWriter(os, "UTF-8")) {
                try (PrintWriter writer = new PrintWriter(osWriter)) {
                    writer.println("我是第一行");
                    writer.print("我的第二行\r\n");
                    writer.printf("%d: 我的第三行\r\n", 1 + 1);

                    writer.flush();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        try (InputStream is = new FileInputStream("output.txt")) {
            try (Scanner scan = new Scanner(is, "UTF-8")) {
                while (scan.hasNext()) {
                    String str = scan.nextLine();
                    System.out.println(str);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
