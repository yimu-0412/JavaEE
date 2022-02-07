import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-02-07-8:28
 */

@MultipartConfig
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 从 req 对象中， 读取到 Part 对象
        Part part = req.getPart("Myimage");
        // 2. 读取到 Part 对象中一些参数
        System.out.println(part.getSubmittedFileName());// 上传文件真实的文件名
        System.out.println(part.getContentType()); // 文件的类型
        System.out.println(part.getSize());// 文件的大小
        // 3. 把文件写入到指定目录中
        part.write("F:\\tmp/Myimage.jpg");
        // 4. 返回一个响应，告诉客户端，上传成功
        resp.getWriter().write("upload ok");
    }


}
