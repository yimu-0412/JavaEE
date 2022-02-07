import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-02-07-16:32
 */

class Person{
    public String name;
    public String phone;

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}


@WebServlet("/each")
public class EachServlet extends HttpServlet {
    private TemplateEngine engine = new TemplateEngine();

    @Override
    public void init() throws ServletException {
        ServletContextTemplateResolver resolver =
                new ServletContextTemplateResolver(this.getServletContext());
        resolver.setPrefix("/WEB-INF/template/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("utf-8");
        engine.setTemplateResolver(resolver);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("公安","110"));
        persons.add(new Person("火警","119"));
        persons.add(new Person("救护车","120"));
        persons.add(new Person("疫木","123"));

        WebContext webContext = new WebContext(req, resp, this.getServletContext());
        webContext.setVariable("persons",persons);
        // engine.process("thymeleafEach",webContext,resp.getWriter());
        String html = engine.process("thymeleafEach", webContext);
        resp.getWriter().write(html);

    }
}
