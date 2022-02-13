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

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-02-13-19:19
 */

@WebServlet("/blog_detail.html")
public class BlogDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 在请求中带有一个参数，blogId，然后根据 blogId 查询数据库，得到详细的内容
        resp.setContentType("text/html;charset=utf-8");
        // 1. 获取到 blogId 参数
        String blogId = req.getParameter("blogId");
        if(blogId == null || "".equals(blogId)){
            String html = "<h3> blogId 字段缺失！</h3>";
            resp.getWriter().write(html);
            return;
        }
        // 2. 根据 blogId 从数据库中进行查询
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.selectOne(Integer.parseInt(blogId));
        if(blog == null){
            String html = "<h3> 当前博客不存在！</h3>";
            resp.getWriter().write(html);
            return;
        }
        // 3. 进行页面渲染
        ServletContext context = this.getServletContext();
        TemplateEngine engine = (TemplateEngine) context.getAttribute("engine");
        WebContext webContext = new WebContext(req,resp,context);
        webContext.setVariable("blog",blog);
        String html = engine.process("blog_detail",webContext);
        resp.getWriter().write(html);

    }
}
