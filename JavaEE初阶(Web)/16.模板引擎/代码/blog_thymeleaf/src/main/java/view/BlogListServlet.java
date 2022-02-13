package view;


import dao.Blog;
import dao.BlogDao;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-02-13-18:18
 */


// @WebServlet 里面的路径是可以包含 . 的
// 后面浏览器中访问的 blog_list.html 不再是静态页面，而是动态生成的
@WebServlet("/blog_list.html")
public class BlogListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        // 1. 从数据库中拿到所有的博客列表
        BlogDao blogDao = new BlogDao();
        List<Blog> blogs = blogDao.selectAll();
        // 2. 通过模板引擎，渲染页面
        ServletContext context = this.getServletContext();
        TemplateEngine engine = (TemplateEngine) context.getAttribute("engine");
        WebContext webContext = new WebContext(req,resp,context);
        webContext.setVariable("blogs",blogs);
        String html = engine.process("blog_list", webContext);
        resp.getWriter().write(html);
    }
}
