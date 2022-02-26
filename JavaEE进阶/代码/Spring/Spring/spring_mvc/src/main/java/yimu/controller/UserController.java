package yimu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import yimu.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangyimu
 * @Program Spring
 * @create 2022-02-20-14:32
 */

@Controller
// @RequestMapping("/user")
public class UserController {

    // 回写数据: 1.直接返回集合或对象（json 格式）
    // 期望 SpringMVC 框架自动将 User 转换成 json 格式的字符串
    @RequestMapping("/quickMethod4")
    @ResponseBody // 告知 SpringMVC 框架，不进行视图跳转，直接进行数据响应
    public User quickMethod4() throws IOException {
        User user = new User();
        user.setName("lisan");
        user.setAge(90);
        return user;
    }


    // 回写数据: 1.直接返回字符串（json 格式）
    @RequestMapping("/quickMethod3")
    @ResponseBody // 告知 SpringMVC 框架，不进行视图跳转，直接进行数据响应
    public String quickMethod3() throws IOException {
        User user = new User();
        user.setName("lisan");
        user.setAge(30);
        // 使用 json 转换工具将对象转换成 json 格式字符串再返回
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        return json;
    }

    // 回写数据: 1.直接返回字符串
    @RequestMapping("/quickMethod2")
    @ResponseBody // 告知 SpringMVC 框架，不进行视图跳转，直接进行数据响应
    public String quickMethod2() throws IOException {
        return "hello SpringMVC";
    }

    // 回写数据: 1.直接返回字符串
    @RequestMapping("/quickMethod1")
    @ResponseBody
    public void quickMethod1(HttpServletResponse response) throws IOException {
        response.getWriter().write("hello world");
    }

    // 1.通过 MVC 框架注入的request对象 setAttribute() 方法设置
    @RequestMapping(value = "/quick5")
    public String save5(HttpServletRequest request){
        request.setAttribute("username","zhansan");
        return "success";
    }

    // 通过将 MOdel 和 View 拆开，返回字符串
    @RequestMapping(value = "/quick4")
    public String save4(Model model){
        model.addAttribute("username","博学");
        return "success";
    }

    @RequestMapping(value = "/quick3")
    public ModelAndView save3(ModelAndView modelAndView){
        // 设置模型数据
        modelAndView.addObject("username","hahaha");
        // 设置视图名称
        modelAndView.setViewName("success");
        return modelAndView;
    }


    @RequestMapping(value = "/quick2")
    public ModelAndView save2(){
        ModelAndView modelAndView = new ModelAndView();
        /*
        * Model:模型 作用封装数据
        * View :视图 作用展示数据
        */
        // 设置模型数据
        modelAndView.addObject("username","itcase");
        // 设置视图名称
        modelAndView.setViewName("success");
        return modelAndView;
    }

    // @RequestMapping(value = "/quick",method = RequestMethod.GET,params = {"username"})
    @RequestMapping(value = "/quick1")
    public String save1(){
        System.out.println("controller save running····");
        // 返回字符串：转发
        // return "success";
        // 返回字符串：重定向
        return "redirect:/index.jsp";
    }
}