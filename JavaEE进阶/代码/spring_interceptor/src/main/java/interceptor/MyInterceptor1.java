package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author
 * @Program
 * @create 2022-03-07-21:37
 */
public class MyInterceptor1 implements HandlerInterceptor {
    // 在目标方法执行之前 执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle····");
        String param = request.getParameter("param");
        if(param.equals("yes")){
            return true;
        }else{
            request.getRequestDispatcher("/error.jsp").forward(request,response);
            return false;//如果返回 true 代表放行，返回false代表不放行。
        }
    }

    // 在目标方法执行之后 视图对象返回之前执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("name","test");
        System.out.println("postHandle····");
    }

    // 在流程执行完毕后 执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion····");
    }
}
