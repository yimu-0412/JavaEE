package bufferDemo;

import jdk.internal.util.xml.impl.Input;

import java.io.*;

/**
 * @author wangyimu
 * @Program 文件的复制
 * @create 2021-10-31-23:09
 */
public class BufferCopyDemo {
    public static void main(String[] args) {
        // 非文本文件路径
        String srcPath1 = "C:\\Users\\Administrator\\Desktop\\比特寒假班\\JAVA\\FileExer1030\\1.avi";
        String destPath1 = "C:\\Users\\Administrator\\Desktop\\比特寒假班\\JAVA\\FileExer1030\\2.avi";

        // 文本文件路径
        String srcPath2 = "C:\\Users\\Administrator\\Desktop\\比特寒假班\\JAVA\\FileExer1030\\test5.txt";
        String destPath2 = "C:\\Users\\Administrator\\Desktop\\比特寒假班\\JAVA\\FileExer1030\\test6.txt";

        long start1 = System.currentTimeMillis();
        // 不同路径下非文本文件的复制
        copyFile1(srcPath1,destPath1);
        long end1 = System.currentTimeMillis();
        System.out.println("非文本文件复制花费的时间为：" + (end1 - start1));

        long start2 = System.currentTimeMillis();
        // 不同路径下文本文件的复制
        copyFile2(srcPath2,destPath2);
        long end2 = System.currentTimeMillis();
        System.out.println("非文本文件复制花费的时间为：" + (end2 - start2));
    }

    // 不同路径下非文本文件的复制
    private static void copyFile1(String srcPath, String destPath) {
        // 1.造文件
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);

        // 2.造流
        // 2.1 造节点流
        try (InputStream is = new FileInputStream(srcFile);
             OutputStream os = new FileOutputStream(destFile)) {
            // 2.2 造缓冲流
            try (BufferedInputStream bis = new BufferedInputStream(is);
                 BufferedOutputStream bos = new BufferedOutputStream(os)) {
                // 3.复制的细节：读取、写入
                int len;
                byte[] cubf = new byte[1024];
                while((len = bis.read(cubf)) != -1){
                    bos.write(cubf,0,len);
                }
                bos.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void copyFile2(String srcPath, String destPath) {
        // 1.造文件
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);

        // 2.造流
        // 2.1 造节点流
        try (FileReader fr = new FileReader(srcFile);
             FileWriter fw = new FileWriter(destFile)) {
            // 2.2 造缓冲流
            try (BufferedReader br = new BufferedReader(fr);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                // 3.复制的细节：读取、写入
                int len;
                char[] cubf = new char[1024];
                while((len = br.read(cubf)) != -1){
                    bw.write(cubf,0,len);
                }
                bw.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
