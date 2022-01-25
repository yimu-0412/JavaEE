import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceProvider;
import java.io.IOException;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-01-25-21:48
 */

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 用户访问这个路径的时候，直接重定向到搜狗主页
        /*resp.setStatus(302);
        resp.setHeader("Location","https://www.sogou.com");*/

        resp.sendRedirect("https://www.sogou.com");
    }
}
