import java.io.File;

/**
 * @author wangyimu
 * @Program FileExer
 * @create 2021-10-18-22:51
 */
public class FileDemo4 {
    public static void main(String[] args) {
        File file = new File("./src");
        // 返回 File 对象代表的目录下的所有文件名
        String[] strs = file.list();
        for(String str: strs){
            System.out.println(str);
        }

        System.out.println();

        // 返回 File 对象代表的目录下的所有文件,以 File 对象表示
        File[] file1 = file.listFiles();
        for(File f: file1 ){
            System.out.println(f);
        }

        System.out.println();
        // 判断用户是否对文件有可读权限
        Boolean isCanRead = file.canRead();
        System.out.println(isCanRead); // true

        System.out.println();
        // 判断用户是否对文件有可写权限
        Boolean isCanWrite = file.canWrite();
        System.out.println(isCanWrite); // true

    }
}
