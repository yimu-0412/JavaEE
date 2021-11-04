package InputStreamReaderTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author wangyimu
 * @Program InputStreamReader的使用
 * @create 2021-11-04-23:21
 */
public class InputStreamReaderTest {
    // InputStreamReader的使用，实现字节的输入流到字符的输入流的转换
    public static void main(String[] args) {
        File file = new File("dbcp.txt");
        try (InputStream is = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(is,"utf-8")) {
             char[] buffer = new char[4096];
             int len;
             while((len = isr.read(buffer)) != -1){
                 String str = new String(buffer,0,len);
                 System.out.println(str);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
