import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author wangyimu
 * @Program 实现图片加密操作
 * @create 2021-11-03-23:08
 */
public class PicTest1 {
    public static void main(String[] args) {

        try (InputStream is = new FileInputStream("1.jpg");
             OutputStream os = new FileOutputStream("1secret.jpg")) {
            byte[] buffer = new  byte[20];
            int len;
            while((len = is.read(buffer)) != -1){
                // 字节数组进行修改
                for(int i = 0; i < len; i ++){
                    buffer[i] = (byte) (buffer[i] ^ 5);
                }
                os.write(buffer,0,len);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
