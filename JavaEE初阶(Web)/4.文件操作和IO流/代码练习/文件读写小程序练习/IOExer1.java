package IOExer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author wangyimu
 * @Program 扫描指定目录，并找到名称中包含指定字符的所有普通文件（不包含目录），
 *          并且后续询问用户是否要删除该文件
 * @create 2021-10-21-21:59
 */
public class IOExer1 {
    public static void main(String[] args) throws IOException {
        // 1.指定一个待扫描的根目录和要查询的关键词
        System.out.println("请输入要扫描的根目录(绝对路径 or 相对路径):");
        Scanner scan = new Scanner(System.in);
        String rootDirPath = scan.next();
        File rootDir = new File(rootDirPath );
        if(!rootDir.isDirectory()){
            System.out.println("输入的根目录不存在或不是目录！程序直接退出");
            return;
        }
        System.out.println("请输入要查找的文件名中的关键词：");
        String token = scan.next();

        // 2.递归的遍历目录
        // result 表示递归遍历的结果，就是包含着所有 token 关键词的文件名
        List<File> result = new ArrayList<>();
        scanDir(rootDir,token,result);
        System.out.println("共找到了符合条件的文件 " + result.size() + " 个，它们分别是");

        // 3.遍历 result ，询问用户是否要删除该文件，根据用户的输入决定是否删除
        for(File f : result){
            System.out.println(f.getCanonicalPath() + "是否要删除?(Y/N)");
            String input = scan.next();
            if(input.equals("Y")){
                f.delete();
            }
        }
    }

    // 递归的遍历目录，找出所有符合条件的目录
    private static void scanDir(File rootDir, String token, List<File> result) throws IOException {
        // list 返回的是一个文件名（String），使用 listFile() 直接得到的是 File 对象，用起来方便一些
        File[] files = rootDir.listFiles();
        if(files == null || files.length == 0 ){
            // 当前目录为空
            return;
        }
        for(File f : files){
            if(f.isDirectory()){
                // 如果当前的文件是一个目录，就递归进行查找
                scanDir(rootDir,token,result);
            }else{
                // 如果当前文件是一个普通文本，就判定该文件是否包含了该关键词
                if(f.getName().contains(token)){
                    result.add(f.getCanonicalFile());
                }
            }
        }
    }
}

