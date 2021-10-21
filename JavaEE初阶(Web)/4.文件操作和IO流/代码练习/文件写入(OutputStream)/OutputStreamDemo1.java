package outputStream;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author wangyimu
 * @Program OutputStreamWriter 进行字符写入方式一:按字节写入
 * @create 2021-10-20-23:49
 */
public class OutputStreamDemo1 {
    public static void main(String[] args) {
        try (OutputStream outputStream = new FileOutputStream("hello.txt")) {
            outputStream.write('h');
            outputStream.write('e');
            outputStream.write('l');
            outputStream.write('l');
            outputStream.write('o');
            outputStream.write(' ');
            outputStream.write('w');
            outputStream.write('o');
            outputStream.write('r');
            outputStream.write('l');
            outputStream.write('d');

            // 不要忘记 flush()
            outputStream.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
