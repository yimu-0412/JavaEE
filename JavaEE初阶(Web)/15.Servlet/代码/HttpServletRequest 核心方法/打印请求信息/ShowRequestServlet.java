import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-01-23-11:31
 */

@WebServlet("/showRequest")
public class ShowRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将生成的响应的 body 放到 respBody 中
        StringBuilder respBody = new StringBuilder();
        // 打印协议的名称和版本
        respBody.append(req.getProtocol());
        respBody.append("<br>");
        // 打印 HTTP 方法的名称
        respBody.append(req.getMethod());
        respBody.append("<br>");
        // 从协议名称直到 HTTP 请求的第一行的查询字符串地址
        respBody.append(req.getRequestURI());
        respBody.append("<br>");
        // 打印请求上下文的请求 URL 部分
        respBody.append(req.getContextPath());
        respBody.append("<br>");
        // 打印包含在路径后的请求 URL 中的查询字符串
        respBody.append(req.getQueryString());
        resp.getWriter().write(respBody.toString());
    }
}
