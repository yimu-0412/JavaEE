package fileOutputStream;

import java.io.*;

/**
 * @author wangyimu
 * @Program FileExer1030
 * @create 2021-10-31-20:44
 */
public class OutputStreamDemo4 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String srcPath = "G:\\尚硅谷java\\尚硅谷Java学科全套教程（总207.77GB）\\" +
                "1.尚硅谷全套JAVA教程--基础必备（67.32GB）\\MySQL核心技术.zip";
        String destPath = "G:\\尚硅谷java\\尚硅谷Java学科全套教程（总207.77GB）\\" +
                "1.尚硅谷全套JAVA教程--基础必备（67.32GB）\\MySQL核心技术1.zip";

        copyFile(srcPath,destPath);

        long end = System.currentTimeMillis();
        System.out.println("复制花费的时间:" + (end - start));
    }

    // 复制指定路径下的文件
    private static void copyFile(String srcPath, String destPath) {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);

        try (InputStream is = new FileInputStream(srcFile);
             OutputStream os = new FileOutputStream(destFile)) {
            int len = 0;
            byte[] cubf = new byte[102400];
            while((len = is.read(cubf)) != -1){
                os.write(cubf,0,len);
                os.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
