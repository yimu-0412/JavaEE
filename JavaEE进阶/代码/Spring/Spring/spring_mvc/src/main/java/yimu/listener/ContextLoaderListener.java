package yimu.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author wangyimu
 * @Program Spring
 * @create 2022-02-20-9:52
 */
public class ContextLoaderListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        // 1. 获取 ServletContext 对象
        ServletContext servletContext = sce.getServletContext();
        // 2. 读取 web.xml 中的全局参数
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");

        // 3. 将 spring 的上下文对象存储到 ServletContext 域中
        ApplicationContext app = new
                ClassPathXmlApplicationContext(contextConfigLocation);
        servletContext.setAttribute("app",app);
        System.out.println("Spring 容器创建完毕！");
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
