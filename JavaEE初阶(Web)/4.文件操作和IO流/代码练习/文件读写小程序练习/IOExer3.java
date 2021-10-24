package IOExer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author wangyimu
 * @Program 扫描指定目录，并找到名称或者内容中包含指定字符的所有普通文件（不包含目录）
 * @create 2021-10-20-0:21
 */
public class IOExer3 {
    public static void main(String[] args) throws IOException {
        // 1. 让用户输入一个待搜索的路径
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要扫描的根目录:");
        String rootDir = scanner.next();
        File rootFile = new File(rootDir);
        if(!rootFile.isDirectory()){
            System.out.println("该目录不存在或者不是文件!直接退出.");
            return;
        }
        // 2. 输入一个查询词,表示要搜索的结果中包含这个词
        System.out.println("请输入要查询的词:");
        String query = scanner.next();

        // 3. 遍历目录以及文件,进行匹配
        List<File> results = new ArrayList<>();
        scanDirWithContent(rootFile,query,results);

        // 4. 把结果打印出来
        for(File f :results){
            System.out.println(f.getCanonicalFile());
        }
    }

    private static void scanDirWithContent(File rootFile, String query, List<File> results) {
        File[] files = rootFile.listFiles();
        if(files == null || files.length == 0){
            // 针对空的目录,直接返回
            return;
        }
        for(File f :files){
            if(f.isDirectory()){
                scanDirWithContent(f,query,results);
            }else{
                if(f.getName().contains(query)){
                    // 看看文件名是否包含
                    results.add(f);
                }else if(isContentContains(f,query)){
                    // 看看文件内容中是否存在
                    results.add(f);
                }
            }
        }
    }

    private static boolean isContentContains(File f, String query) {
        // 打开 f 这个文件,一次取出每一行结束,去和 query 进行一个 indexOf()
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = new FileInputStream(f)) {
            Scanner scan = new Scanner(inputStream,"UTF-8");
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                stringBuilder.append(line + "\n");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        // 只要结果不等于 -1,说明查到了
        return stringBuilder.indexOf(query) != -1;
    }
}
