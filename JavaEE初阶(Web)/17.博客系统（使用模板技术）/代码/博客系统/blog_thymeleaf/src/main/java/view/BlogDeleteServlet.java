package view;

import common.Util;
import dao.Blog;
import dao.BlogDao;
import dao.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-02-14-23:12
 */
@WebServlet("/blogDelete")
public class BlogDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        // 1. 验证当前用户是否登录，未登录状态不能删除文章
        Users user = Util.checkLoginStatus(req);
        if(user == null){
            String html = "<h3> 当前用户未登录，不能删除文章 </h3>";
            resp.getWriter().write(html);
            return;
        }
        // 2. 获取到当前请求中要删除的博客 id
        String blogId = req.getParameter("blogId");
        if(blogId == null || "".equals(blogId)){
            String html = "<h3> blogId 参数缺失！ </h3>";
            resp.getWriter().write(html);
            return;
        }
        // 3. 校验当前执行删除操作的是否为作者
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.selectOne(Integer.parseInt(blogId));
        if(blog.getUserId() != user.getUserId()){
            String html = "<h3> 您不是作者，不能删除！</h3>";
            resp.getWriter().write(html);
            return;
        }

        // 4. 进行删除操作
        blogDao.delete(Integer.parseInt(blogId));
        // 5. 重定向到博客列表页
        resp.sendRedirect("blog_list.html");
    }
}
