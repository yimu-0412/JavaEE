import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-02-03-14:44
 */
@WebServlet("/indexServlet")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 根据当前用户请求中的 sessionId，获取到用户信息，并展示到页面上
        resp.setContentType("text/html;charset=utf-8");
        // 1. 判断当前用户是否已经登录，（请求中没有 sessionId ，以及 sessionId 是否合法）
        //    如果会话不存在，就不能创造了，只是查询，不是登录
        HttpSession httpSession = req.getSession(false);
        if(httpSession == null){
            // 当前没有合法的会话，当前用户尚未登录，重定向到 login，html ，让用户进行登录
            resp.sendRedirect("login.html");
            return;
        }
        // 2. 如果用户已经登录，就可以从 httpSession 中拿到用户信息了
        String username = (String) httpSession.getAttribute("username");
        int loginCount = (int) httpSession.getAttribute("loginCount");
        loginCount = loginCount + 1;
        httpSession.setAttribute("loginCount",loginCount);
        // 3. 返回一个 HTML 页面
        StringBuilder html = new StringBuilder();
        html.append("<div>用户" + username + "</div");
        html.append("<div>访问次数" + loginCount + "</div>");
        resp.getWriter().write(html.toString());
    }
}
