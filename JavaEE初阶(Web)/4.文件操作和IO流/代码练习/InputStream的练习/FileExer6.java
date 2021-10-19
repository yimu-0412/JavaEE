
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wangyimu
 * @Program FileExer
 * @create 2021-10-19-23:47
 */
public class FileExer6 {
    public static void main(String[] args) throws IOException {
        try (InputStream is = new FileInputStream("hello.txt")) {
            byte[] buf = new byte[1024];
            int len;
            while (true) {
                len = is.read(buf);
                if (len == -1) {
                // 代表文件已经全部读完
                    break;
                }
                for (int i = 0; i < len; i++) {
                    System.out.printf("%c", buf[i]);
                }
            }
        }
    }
}
