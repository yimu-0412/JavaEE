import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyimu
 * @Program FileExer
 * @create 2021-10-18-21:02
 */
// 通过递归的方式,罗列指定目录中的所用文件路径
public class FileDemo1 {
    public static List<String> result = new ArrayList<>();
    public static void getAllFiles(String basePath){
        File file = new File(basePath);
        if(file.isFile()){
            result.add(basePath);
        }else{

        }
    }
    public static void main(String[] args) {

    }
}
