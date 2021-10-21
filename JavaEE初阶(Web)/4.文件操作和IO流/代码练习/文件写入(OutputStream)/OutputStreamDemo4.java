package outputStream;

import java.io.*;
import java.util.Scanner;

/**
 * @author wangyimu
 * @Program OutputStreamWriter 进行字符写入方式四:
 *          PrintWriter 进行输入,然后读取写入的内容
 * @create 2021-10-21-20:35
 */
public class OutputStreamDemo4 {
    public static void main(String[] args) {
        try (OutputStream os = new FileOutputStream("print.txt")) {
            // 使用的字符编码集是 UTF-8

            try (OutputStreamWriter osWriter = new OutputStreamWriter(os, "UTF-8")) {
                try (PrintWriter writer = new PrintWriter(osWriter)) {
                    // 接下来就可以使用 writer 提供的各种方法了
                    writer.println("hello world");
                    writer.println("你好 世界");
                    writer.printf("%d:%s\n",1,"没什么");
                    writer.println("王以牧");
                    writer.println("232");

                    writer.flush();
                }
            }


        }catch(Exception e){
            e.printStackTrace();
        }
        try (InputStream is = new FileInputStream("print.txt")) {
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
