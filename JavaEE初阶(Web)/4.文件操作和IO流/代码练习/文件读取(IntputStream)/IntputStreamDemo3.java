package inputStream;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author wangyimu
 * @Program 文件读取方式三:利用 Scanner 进行字符读取
 * @create 2021-10-20-23:29
 */
public class IntputStreamDemo3 {
    public static void main(String[] args) {
        // 尝试从文件中读取中文,借助标准库中的处理字符的方式
        // Scanner 不光能从控制台读取标准输入,也可以从文件中读取数据
        try (InputStream inputStream = new FileInputStream("./src/main.txt")) {
            try (Scanner scan = new Scanner(inputStream, "UTF-8")) {
                while(scan.hasNext()){
                    String s = scan.nextLine();
                    System.out.println(s);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
