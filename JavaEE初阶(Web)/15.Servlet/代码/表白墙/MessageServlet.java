import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-01-25-22:40
 */

class Message{
    public String from;
    public String to;
    public String message;
}

@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    private ObjectMapper objectMapper= new ObjectMapper();
    // private List<Message> messageList= new ArrayList<Message>();

    // 这个是保存文件的路径
    private String filePath = "C:\\Users\\Administrator" +
            "\\Desktop\\比特寒假班\\JAVA\\2022\\messagewall\\src\\main\\messages.txt";

    @Override
    public void init() throws ServletException {
        // 在这个里面给 messageList 构造测试数据，方便观察
        // 这个数据的构造可以写到 init 里面，也可以写到构造方法里面
        Message message = new Message();
        message.from = "白猫";
        message.to = "黑猫";
        message.message = "喵";
        //essageList.add(message);

        message = new Message();
        message.from = "橘猫";
        message.to = "华猫";
        message.message = "miaomiao!";
        // messageList.add(message);

    }

    // 处理从服务器获取到的消息数据
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");

        List<Message> messageList = load();
        objectMapper.writeValue(resp.getWriter(),messageList);
    }

    private List<Message> load() {
        // 读取文件，将读取的数据放入到 List<Message> 中
        List<Message> messageList= new ArrayList<Message>();
        System.out.println("从文件加载！");
        // 此处需要按行读取，FileReader 不支持，需要加上 BufferedReader
        // 使用 Scanner 也可以
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            while (true){
                String line = br.readLine();
                if(line == null){
                    System.out.println("读取完毕！");
                    break;
                }

                // 如果读取到line 的内容，就把 line 解析成一个 Message 对象
                String[] tokens = line.split("\t") ;
                Message message = new Message();
                message.from = tokens[0];
                message.to = tokens[1];
                message.message = tokens[2];
                messageList.add(message);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messageList;
    }

    // 从客户端提交数据到服务器

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Message message = objectMapper.readValue(req.getInputStream(), Message.class);
        // messageList.add(message);

        // 进行一个写文件的操作
        save(message);
        
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write("{\"ok\":1}");
    }

    private void save(Message message) {
        System.out.println("向文件中写入数据！");
        try(FileWriter fw = new FileWriter(filePath,true)) {
             fw.write(message.from + "\t" + message.to + "\t" + message.message + "\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
