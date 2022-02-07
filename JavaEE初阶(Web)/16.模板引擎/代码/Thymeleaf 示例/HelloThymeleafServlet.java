import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

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
 * @create 2022-02-07-11:54
 */
@WebServlet("/helloThymeleaf")
public class HelloThymeleafServlet extends HttpServlet {
    private TemplateEngine engine = new TemplateEngine();
    @Override
    public void init() throws ServletException {
        // 完成 Thymeleaf 的初始化操作

        // 创建 模板解析器 对象
        ServletContextTemplateResolver resolver =
                  new ServletContextTemplateResolver(this.getServletContext());
        // 让 模板解析器，来加载模板文件,这里的前缀就代表模板文件所在的目录
        // 这里的前缀和后缀，就是告诉模板引擎，要加载那些文件到内存中，以备后用
        resolver.setPrefix("/WEB-INF/template/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("utf-8");
        // 把解析器对象，给设置到 engine 对象中
        engine.setTemplateResolver(resolver);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 在模板渲染（将写好的模板 html 代码里面的 message 变量进行替换）之前，要先进行初始化
        //    初始化操作只要执行一次就行
        // 2. 先从参数中读取用户要传过来的 message 的值（从 query String 中读取）
        String message = req.getParameter("message");
        // 3. 把当前从请求中读取出来的 message 和 ${message} 关联起来
        WebContext  webContext = new WebContext(req,resp, this.getServletContext());
        webContext.setVariable("message",message);
        // 4.进行最终的渲染
        engine.process("hello",webContext,resp.getWriter());
    }
}
