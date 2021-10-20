import java.io.*;

/**
 * @author wangyimu
 * @Program 文件读取方式一:逐字节读取
 * @create 2021-10-19-20:07
 */

public class FileDemo5 {
    public static void main(String[] args) throws IOException {
        // 这个创建实例的过程,就是在打开文件
        // 要先打开文件.然后才能进行练习
        File file = new File("hello.txt");
        try(InputStream inputStream = new FileInputStream(file);) {
            // 逐个字节的方式把文件内容读取出来
            while(true){
                // 每次调用 read 就可以读取一些数据出来
                // read 提供了好几个版本,其中无参数版本就是一次读取一个字节
                // 对于无参数版本的 read 来说,返回值就是这次读到的字节
                // 这个结果的范围是 0 ~ 255
                // 如果读到文件夹末尾(EOF,end of file),此时继续进行 read .就会返回 -1;
                int b = inputStream.read();
                if(b == -1){
                    break;
                }
                System.out.printf("%c", b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
