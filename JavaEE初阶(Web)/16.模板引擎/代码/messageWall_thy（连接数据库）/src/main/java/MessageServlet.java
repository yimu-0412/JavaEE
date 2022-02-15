import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-02-13-23:06
 */
class Message {
    public String from;
    public String to;
    public String message;
}

@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    // 处理从服务器获取到的消息数据
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        // GET 方法获取页面内容
        // 1. 从数据库读取数据
        List<Message> messages = load();
        // 2. 再进行页面渲染
        ServletContext context = getServletContext();
        TemplateEngine engine = (TemplateEngine) context.getAttribute("engine");
        WebContext webContext = new WebContext(req, resp, context);
        webContext.setVariable("messages", messages);
        engine.process("message", webContext, resp.getWriter());
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
        // POST 方法发布新留言
        // 1. 从参数中获取到消息数据
        // [注意] 要给 req 也设置字符集, 否则读取表单中的中文可能就会乱码
        req.setCharacterEncoding("utf-8");
        Message message = new Message();
        message.from = req.getParameter("from");
        message.to = req.getParameter("to");
        message.message = req.getParameter("message");
        // 2. 把数据插入到数据库中
        save(message);
        resp.setContentType("application/json;charset=utf-8");
        // 3. 重定向到 GET 方法的页面
        resp.sendRedirect("message");
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
