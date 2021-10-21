package outputStream;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author wangyimu
 * @Program OutputStreamWriter 进行字符写入方式三:按字符串写入
 * @create 2021-10-21-0:01
 */
public class OutputStreamDemo3 {
    public static void main(String[] args) {
        try (OutputStream outputStream = new FileOutputStream("hello2.txt")) {
            String str1 = "hello java2";
            byte[] buffer1 = str1.getBytes();
            outputStream.write(buffer1);

            String str2 = "\n 你好 中国";
            byte[] buffer2 = str2.getBytes("UTF-8");
            outputStream.write(buffer2);

            // 不要忘记 fulsh()
            outputStream.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

