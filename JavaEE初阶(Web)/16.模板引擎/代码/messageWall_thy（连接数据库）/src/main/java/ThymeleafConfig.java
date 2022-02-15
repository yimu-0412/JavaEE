import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-02-13-23:05
 */
@WebListener
public class ThymeleafConfig implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        // 1. 初始化 Template
        TemplateEngine engine = new TemplateEngine();
        // 2. 初始化 ServletContextTemplateResolver
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(context);
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("utf-8");
        // 3. 将 resovler 和 engine 关联起来
        engine.setTemplateResolver(resolver);
        // 4. 将 engine 设置到 ServletContext 中
        context.setAttribute("engine",engine);
        System.out.println("TemplateEngine 初始化完毕！");
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
