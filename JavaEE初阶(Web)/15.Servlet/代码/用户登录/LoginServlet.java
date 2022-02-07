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
 * @create 2022-02-03-14:18
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 先从请求的 body 中读取 用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 2. 判断用户名和密码是否正确
        if(!"zhangsan".equals(username) || !"123" .equals(password)){
            // 登录失败！
            resp.getWriter().write("登陆失败");
        }
        System.out.println("登陆成功！");
        // 3. 登陆成功，创建出一个会话
        //    会话是根据请求中的 SessionId 来查的，sessionId 是 Cookie 中的
        //    但是此处是首次登录，此时请求中是没有 Cookie的（Cookie 是服务器返回的）
        //    此时就会触发“找不到就创建的流程”
        //    同时这里进行的操作：先创建一个 HttpSession 对象（作为 value）
        //    在生成一个随机的字符串，作为 SessionId （作为 key）
        //    把这个 key 和 value 插入到 hash 表中
        //    同时把这个生成的 sessionId 通过 set-Cookie 字段返回给浏览器
        HttpSession httpSession = req.getSession(true);
        //     还可以存入程序员自定义的数据，还可以存入身份信息（用户名和登录次数）
        httpSession.setAttribute("username","zhansan");
        httpSession.setAttribute("loginCount",Integer.valueOf(0));
        // 4. 让页面跳转到主页，主要通过重定向的方式实现
        resp.sendRedirect("indexServlet");
    }
}
