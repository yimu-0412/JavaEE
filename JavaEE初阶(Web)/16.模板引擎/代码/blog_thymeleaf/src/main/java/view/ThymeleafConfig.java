package view;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-02-13-17:57
 */

@WebListener
public class ThymeleafConfig implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        /*ServletContext context = sce.getServletContext();
        // 1. 创建 TemplateEngine 实例
        TemplateEngine engine = new TemplateEngine();
        // 2. 创建 resolver 对象
        ServletContextTemplateResolver resolver = new
                ServletContextTemplateResolver(context);
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("utf-8");
        // 3.将 resolver 对象和 engine 关联起来
        engine.setTemplateResolver(resolver);
        // 4. 将创建好的 engine 对象放到 ServletContext 中
        context.setAttribute("engine",engine);
        System.out.println("TemplateEngine 初始化完成！");*/

        // 1. 获取 context 对象
        ServletContext context = sce.getServletContext();
        // 2. 创建 TemplateEngine 实例
        TemplateEngine engine = new TemplateEngine();
        // 3. 创建 ServletContextTemplateResolver
        ServletContextTemplateResolver resolver = new
                ServletContextTemplateResolver(context);
        // 4. 给 resolver 设置一些属性
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("utf-8");
        // 5. 关联 engine 和 resolver
        engine.setTemplateResolver(resolver);
        // 6. 将 engine 放入 SerletContext 中，以备其他的 Servlet 类使用
        context.setAttribute("engine",engine);
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
