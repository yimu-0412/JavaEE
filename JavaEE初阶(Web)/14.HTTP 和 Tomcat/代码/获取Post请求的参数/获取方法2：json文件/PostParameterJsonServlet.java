import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-01-23-15:18
 */

// 通过这个类来表示解析后的结果
class JsonData{
    public int userId;
    public int classId;
}

@WebServlet("/postParameterJson")
public class PostParameterJsonServlet extends HttpServlet {
    @Override
    protected void  doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先将整个 body 读取
        String body = readBody(req);
        // 使用 Jackson 进行解析
        ObjectMapper objectMapper = new ObjectMapper();
        JsonData jsonData = objectMapper.readValue(body, JsonData.class);
        resp.getWriter().write(String.format("userId: %d;classId: %d <br>",
                jsonData.userId,jsonData.classId));
    }

    private String readBody(HttpServletRequest req) throws IOException {
        // 读取 body 需要根据 req getInputStream 得到一个流对象，从这个流对象中进行读取
        InputStream inputStream = req.getInputStream();
        // 通过 contentLength 拿到请求中的 body 的字节数
        int contentLength = req.getContentLength();
        byte[] buffer = new byte[contentLength];
        inputStream.read(buffer);
        return new String(buffer,"utf-8");
    }
}
