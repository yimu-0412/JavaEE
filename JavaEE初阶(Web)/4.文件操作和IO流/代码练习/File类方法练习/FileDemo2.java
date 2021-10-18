import java.io.File;
import java.io.IOException;

/**
 * @author wangyimu
 * @Program FileExer
 * @create 2021-10-18-22:17
 */
public class FileDemo2 {
    public static void main(String[] args) throws IOException {
        //  根据文件路径创建一个新的 File 实例，路径可以是绝对路径或者相对路径
        File file = new File("./src/test.txt");

        // 返回 File 对象的父目录文件路径
        String str1 = file.getParent();
        System.out.println(str1);
        // .\src

        // 返回 FIle 对象的纯文件名称
        String str2 = file.getName();
        System.out.println(str2);
        // test.txt

        // 返回 File 对象的文件路径
        String str3 = file.getPath();
        System.out.println(str3);
        // .\src\test.txt

        // 返回 File 对象的绝对路径
        String str4 = file.getAbsolutePath();
        System.out.println(str4);
        // C:\Users\Administrator\Desktop\比特寒假班\JAVA\FileExer\.\src\test.txt

        // 返回 File 对象的修饰过的绝对路径
        String str5 = file.getCanonicalPath();
        System.out.println(str5);
        // C:\Users\Administrator\Desktop\比特寒假班\JAVA\FileExer\src\test.txt

        // 判断 File 对象描述的文件是否存在
        Boolean isExists = file.exists();
        System.out.println(isExists); // true

        // 判断 File 对象代表的文件是否是一个目录
        Boolean isDirectory = file.isDirectory();
        System.out.println(isDirectory); // false

        // 判断 File 对象代表的文件是否是一个普通文件
        Boolean isFile = file.isFile();
        System.out.println(isFile); // true
    }

}