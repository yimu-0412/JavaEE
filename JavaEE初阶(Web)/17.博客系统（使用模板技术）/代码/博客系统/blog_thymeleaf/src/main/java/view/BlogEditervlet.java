package view;

import common.Util;
import dao.Blog;
import dao.BlogDao;
import dao.Users;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;


/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-02-14-21:44
 */

@WebServlet("/blog_edit")
public class BlogEditervlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        // 1. 验证当前用户是否登录，未登录状态不能发布文章
        Users user = Util.checkLoginStatus(req);
        if(user == null){
            String html = "<h3> 当前用户未登录，不能发布文章 </h3>";
            resp.getWriter().write(html);
            return;
        }

        // 2. 读取请求中的参数
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        if(title == null || "".equals(title) || content == null || "".equals(content)){
            String html = "<h3> 提交的 title 或者 content 不存在！发布博客失败！</h3>";
            resp.getWriter().write(html);
            return;
        }
        // 3. 构造 Blog 对象，插入到数据库中
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        // 博客的作者，正是提交者的 userId
        blog.setUserId(user.getUserId());
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));

        BlogDao blogDao = new BlogDao();
        blogDao.insert(blog);

        // 4.重定向到博客列表页
        resp.sendRedirect("blog_list.html");
    }
}
