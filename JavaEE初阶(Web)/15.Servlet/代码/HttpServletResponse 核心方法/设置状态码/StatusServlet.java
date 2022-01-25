import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-01-25-19:40
 */

@WebServlet("/status")
public class StatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        // 让用户传入一个请求
        // 请求在 query String 带一个参数，就表示响应的状态码
        // 然后根据用户的输入，返回不同的状态码的响应
        String statusString = req.getParameter("status");
        if(statusString == null || statusString == ""){
            resp.getWriter().write("当前的请求参数 status  缺失");
            return;
        }
        resp.setStatus(Integer.parseInt(statusString));
        resp.getWriter().write("status： " + statusString);

    }
}
