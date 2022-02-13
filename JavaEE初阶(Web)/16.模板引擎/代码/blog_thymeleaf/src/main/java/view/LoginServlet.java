package view;



import dao.Users;
import dao.UsersDao;

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
 * @create 2022-02-13-19:48
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        // 1. 先从请求中读取出 用户名 和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username == null || "".equals(username) ||
                password == null || "".equals(password)){
            String html = "<h3>登录失败！缺少用户名或者密码</h3>";
            resp.getWriter().write(html);
            return;
        }
        // 2. 从数据库中根据用户名查询密码
        UsersDao usersDao = new UsersDao();
        Users user = usersDao.selectByName(username);
        if(user == null){
            String html = "<h3>登录失败！用户名或者密码错误</h3>";
            resp.getWriter().write(html);
            return;
        }
        // 3. 把从数据库中查出的密码和用户数输入的密码就行比较
        if(!user.getPassword().equals(password)){
            String html = "<h3>登录失败！用户名或者密码错误</h3>";
            resp.getWriter().write(html);
            return;
        }
        // 4. 登录如果成功，就把当前的 user 对象给存到 Httpsession 中
        HttpSession session = req.getSession(true);
        session.setAttribute("user",user);
        // 5. 登录成功后，重定向到博客列表页
        resp.sendRedirect("blog_list.html");

    }
}
