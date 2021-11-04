package InputStreamReaderTest;

import java.io.*;

/**
 * @author wangyimu
 * @Program 转换流的综合使用
 * @create 2021-11-04-23:35
 */
public class Test {
    public static void main(String[] args) {
        // 1.造文件，造流
        File file1 = new File("dbcp.txt");
        File file2 = new File("dbcp_gbk.txt");
        try (InputStream fis = new FileInputStream(file1);
             OutputStream fos = new FileOutputStream(file2);
             InputStreamReader isr = new InputStreamReader(fis,"utf-8");
             OutputStreamWriter osw = new OutputStreamWriter(fos, "gbk")) {
             // 2.读写过程
            char[] cubf = new char[40];
            int len;
            while((len = isr.read(cubf)) != -1){
                osw.write(cubf,0,len);
                osw.flush();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
