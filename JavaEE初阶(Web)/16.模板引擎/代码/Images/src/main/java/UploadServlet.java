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
 * @create 2022-02-08-18:20
 */
// 这个注解在上传文件的功能中是必要的
@MultipartConfig
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = getServletContext().getRealPath("/image");
        Part part = req.getPart("Myimage");
        part.write(path + "/" + part.getSubmittedFileName());
        resp.sendRedirect("image");
    }
}
