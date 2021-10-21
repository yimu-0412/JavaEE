package inputStream;

import java.io.DataOutput;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wangyimu
 * @Program 文件读取方式二:字节数组读取
 * @create 2021-10-19-23:47
 */
public class IntputStreamDemo2 {
    public static void main(String[] args) throws IOException {
        try (InputStream inputStream = new FileInputStream("hello.txt")) {
            byte[] buffer = new byte[1024];
            while(true){
                int len = inputStream.read(buffer);
                if(len == -1){
                    break;
                }
                for(int i = 0;i < len;i ++){
                    System.out.printf("%c",buffer[i]);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
