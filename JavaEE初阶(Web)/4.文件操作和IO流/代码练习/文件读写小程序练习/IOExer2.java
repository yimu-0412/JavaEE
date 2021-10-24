package IOExer;

import inputStream.IntputStreamDemo1;

import java.io.*;
import java.util.Scanner;

/**
 * @author wangyimu
 * @Program 进行普通文件的复制
 * @create 2021-10-21-22:49
 */
public class IOExer2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入要复制的文件(绝对路径)：");
        String srcPath = scan.next();
        File srcFile = new File(srcPath);
        if(srcFile.isFile()){
            System.out.println("文件路径错误！程序直接退出");
            return;
        }
        System.out.println("请输入要复制到目标路径(绝对路径)：");
        String destPath = scan.next();
        File destFile = new File(destPath);
        // 要求这个 destFile 必须不能存在
        if(destFile.exists()){
            System.out.println("目标文件的目录已存在！程序直接退出");
            return;
        }
        if(!destFile.getParentFile().exists()){
            // 父级目录不存在，也提示一个报错，也可以不存在就创建出来，使用 makeDir 就可以创建
            System.out.println("目标文件的父级目录不存在！程序直接退出");
            return;
        }

        // 进行复制操作
        // 复制操作就是打开待复制的文件，读取每个字节，然后再把这些字节给写入的到目标的文件中
        try (InputStream is = new FileInputStream(srcFile)) {
            try (OutputStream os = new FileOutputStream(destFile)) {
                // 从 is 中按照字节来读，然后再把结果写入 os 中
                while(true){
                    byte[] buffer = new byte[1024];
                    int len = is.read(buffer);
                    if(len == -1){
                        break;
                    }
                    os.write(buffer,0,len);
                }
                // 如果这里不加 flush，触发 close 操作，也会自动刷新缓冲区
                os.flush();
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
