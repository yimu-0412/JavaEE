package outputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author wangyimu
 * @Program OutputStreamWriter 进行字符写入方式二:按字节数组写入
 * @create 2021-10-20-23:54
 */
public class OutputStreamDemo2 {
    public static void main(String[] args) {
        try (OutputStream outputStream = new FileOutputStream("hello1.txt")) {
            byte[] buffer = new byte[]{'h','e','l','l','o',' ','j','a','v','a'};
            outputStream.write(buffer);

            // 不要忘记 flush()
            outputStream.flush();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
