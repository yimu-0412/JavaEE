import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-02-07-9:09
 */
@WebServlet("/guessNum")
public class GuessNumServlet extends HttpServlet {
    private TemplateEngine engine = new TemplateEngine();

    private int toGuess = 0; // 表示要猜的数字
    private int count = 0; // 表示猜的次数

    @Override
    public void init() throws ServletException {
        // 对模板引擎进行初始化
        ServletContextTemplateResolver resolver =
                new ServletContextTemplateResolver(this.getServletContext());
        resolver.setPrefix("/WEB-INF/template/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("utf-8");
        engine.setTemplateResolver(resolver);
    }

    // 获取到页面的初始情况，并且初始化，生成一个待猜的数字
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        // 1. 生成一个待猜的数字
        Random random = new Random();
        // [0,100] + 1 => [1,100]
        toGuess = random.nextInt(100) + 1;
        count = 0;
        // 2. 返回一个页面
        //    这里开始一局新的游戏
        WebContext webContext = new WebContext(req, resp, this.getServletContext());
        webContext.setVariable("newGame",true);
        engine.process("guessNum",webContext,resp.getWriter());

        /*
        StringBuilder html = new StringBuilder();
        html.append("<form action=\"guessNum\" method=\"POST\">");
        html.append("<input type=\"text\" name=\"num\">");
        html.append("<input type=\"submit\" value=\"提交\">");
        html.append("</form>");
        resp.getWriter().write(html.toString());*/
    }

    // 处理一次待猜的过程
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        // 1. 从请求中，读取出用户提交的数字的内容'
        String parameter = req.getParameter("num");
        int num = Integer.parseInt(parameter);
        String result = "";

        // 2. 和 toGuess 进行比较
        if(num < toGuess){
            result = "猜低了";
        }else if(num > toGuess){
            result = "猜高了";
        }else{
            result = "猜对了";
        }
        // 3. 自增猜的次数
        count ++;
        // 4. 构造一个结果页面，能够显示当前猜的结果
        WebContext webContext = new WebContext(req, resp, this.getServletContext());
        webContext.setVariable("newGame",false);
        webContext.setVariable("result",result);
        webContext.setVariable("count",count);
        engine.process("guessNum",webContext,resp.getWriter());


        /*StringBuilder html = new StringBuilder();
        html.append("<form action=\"guessNum\" method=\"POST\">");
        html.append("<input type=\"text\" name=\"num\">");
        html.append("<input type=\"submit\" value=\"提交\">");
        html.append("</form>");

        // 新增一些标签，显示猜的结果
        html.append("<div>" + result + "</div>");
        html.append("<div>猜的次数： " + count + " 次" + "</div>");
        resp.getWriter().write(html.toString());*/
    }
}
