import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
       
        System.out.println("从数据库读取数据");

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from message";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Message message = new Message();
                message.from = resultSet.getString("from");
                message.to = resultSet.getString("to");
                message.message = resultSet.getString("message");
                messageList.add(message);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
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
       
        System.out.println("向数据库中写入数据");
        

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // 1.先和数据库建立连接
            connection = DBUtil.getConnection();
            // 2. 构造拼装 SQL
            String sql = "insert into message values(?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,message.from);
            statement.setString(2,message.to);
            statement.setString(3,message.message);
            // 3.执行 SQL
            int ret = statement.executeUpdate();
            if(ret == 1){
                System.out.println("插入成功");
            }else{
                System.out.println("插入失败!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }


    }
}






























