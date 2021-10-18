import java.io.File;
import java.io.IOException;

/**
 * @author wangyimu
 * @Program FileExer
 * @create 2021-10-18-22:36
 */
public class FileDemo3 {
    public static void main(String[] args) throws IOException {
        File file1 = new File("./src/test1.txt");
        // 根据 File 对象,自创建一个空文件,成功后返回true
        Boolean creatFile1 = file1.createNewFile();
        System.out.println(creatFile1);
        // true 目录文件没有此文件,创建成功

        File file2 = new File("./src/test.txt");
        Boolean creatFile2 = file2.createNewFile();
        System.out.println(creatFile2);
        // false  目录文件已有此文件,创建失败

        // 根据 File 对象,删除该文件,成功删除后返回 true
        Boolean isDelect = file2.delete();
        System.out.println(isDelect);

        // 根据 File 对象.标注文件将被删除,删除动作回到 JVM 运行结束才会进行

        file1.deleteOnExit();

    }
}
