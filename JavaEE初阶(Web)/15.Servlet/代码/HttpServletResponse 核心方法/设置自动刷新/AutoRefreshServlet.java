import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-01-25-19:58
 */

@WebServlet("/refresh")
public class AutoRefreshServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setHeader("Refresh","1");
        // 获取毫秒级时间戳
        long timeStamp = System.currentTimeMillis();
        resp.getWriter().write("timeStamp: " + timeStamp);
    }
}
