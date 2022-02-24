package yimu.web;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import yimu.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangyimu
 * @Program Spring
 * @create 2022-02-20-0:57
 */

// @WebServlet("/userService")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*ApplicationContext app = new
                ClassPathXmlApplicationContext("appc.xml");*/
        ServletContext servletContext = req.getServletContext();
        // ApplicationContext app = (ApplicationContext) servletContext.getAttribute("app");
        // ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        UserService userService = (UserService) app.getBean("userService");
        userService.save();

    }
}
