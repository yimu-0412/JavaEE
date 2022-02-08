import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-02-08-16:56
 */
@WebListener
public class ThymeleafConfig implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        // 1. 创建 engine 实例
        TemplateEngine engine = new TemplateEngine();
        // 2. 创建 resovler 实例
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(context);
        resolver.setPrefix("/WEB-INF/template/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("utf-8");
        // 3. 将 engine 和 resovler 关联起来
        engine.setTemplateResolver(resolver);
        // 4. 把 engine 放入 ServletContext 里面
        context.setAttribute("engine",engine);
        System.out.println("TemplatEngine 初始化完毕");
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
