import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-02-08-17:27
 */

class Image{
    public String name;
    public String url;

}
@WebServlet("/image")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        // 1. 扫描指定的路径 /webapp/image,看看有多少个图片，构造成 List<Image>
        List<Image> images = loadImage();
        // 2. 将 images 构造到模板页面
        ServletContext context = this.getServletContext();
        TemplateEngine engine = (TemplateEngine) context.getAttribute("engine");
        // 3. 构建好 WebContext
        WebContext webContext = new WebContext(req, resp, context);
        webContext.setVariable("images",images);
        // 4. engine 进行渲染操作
        String html = engine.process("images", webContext);
        resp.getWriter().write(html);

    }

    private List<Image> loadImage() {
        // 扫描指定的路径 /webapp/image 目录
        List<Image> images = new ArrayList<Image>();
        // 需要获取到 /webapp/image 这个目录在磁盘上的绝对路径
        ServletContext context = this.getServletContext();
        String path = context.getRealPath("/image");
        System.out.println(path);
        // 根据路径来查看有哪些图片
        File imageBoot = new File(path);
        File[] files = imageBoot.listFiles();
        for(File f: files){
            Image image = new Image();
            image.name = f.getName();
            image.url = "image/" + f.getName();
            images.add(image);
        }
        return images;
    }


}
