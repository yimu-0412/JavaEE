package yimu.controller;

/**
 * @author
 * @Program
 * @create 2022-02-26-10:04
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yimu.domain.User;
import yimu.domain.VO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController1 {
    // 1.获取基本参数类型
    @RequestMapping("/quick1")
    @ResponseBody
    public void quickMethod1(String name,int age){
        System.out.println(name);
        System.out.println(age);
    }

    // 2.获得 POJO 类型数据
    @RequestMapping("/quick2")
    @ResponseBody
    public void quickMethod2(User user){
        System.out.println(user);
    }

    // 3.获得数组类型参数
    @RequestMapping("/quick3")
    @ResponseBody
    public void quickMethod3(String[] strs){
        System.out.println(Arrays.asList(strs));
    }

    // 4.获取集合类型数据: 通过 form 提交时，一般将集合封装到一个POJO中
    // 通过配置 filter 全局配置进行字符集编码的设定
    @RequestMapping("/quick4")
    @ResponseBody
    public void quickMethod4(VO vo){
        System.out.println(vo);
    }

    // 4.获取集合类型数据: 通过 ajax 提交
    // @RequestBody 格式必须是 json 格式的
    @RequestMapping("/quick5")
    @ResponseBody
    public void quickMethod5(@RequestBody List<User> userList){
        System.out.println(userList);
    }

    // 5.参数绑定注解 @RequestParam
    // required: 当前指定的参数是否必须包括，默认是 true，提交时如果没有此参数则会报错
    // defaultValue: 当没有指定请求参数时，则使用指定的默认值赋值
    @RequestMapping("/quick6")
    @ResponseBody
    public void quickMethod6(@RequestParam(value="name",required = false,defaultValue = "itcase") String username){
        System.out.println(username);
    }

    // 6.获得 Restful 风格的参数
    @RequestMapping("/quick7/(username)")
    @ResponseBody
    public void quickMethod7(@PathVariable(value = "username") String username){
        System.out.println(username);
    }

    // 7.自定义类型转换器
    // 步骤1：定义转换器类实现 Converter 接口
    // 步骤2：在配置文件中声明转换器
    // 步骤3：在<annation-drien>中引用转换器
    @RequestMapping("/quick8")
    @ResponseBody
    public void quickMethod8(Date date){
        System.out.println(date);
    }

    // 7.获得Servlet相关API
    @RequestMapping("/quick9")
    @ResponseBody
    public void quickMethod9(HttpServletRequest req, HttpServletResponse response, HttpSession session){
        System.out.println(req);
        System.out.println(response);
        System.out.println(session);
    }

    // 8.获取请求头: @RequestHeader
    @RequestMapping("/quick10")
    @ResponseBody
    public void quickMethod10(@RequestHeader(value = "User-Agent",required = false) String User_Agent){
        System.out.println(User_Agent);
    }

    // 8.获取请求头: @CookieValue(JSESSIONID)
    @RequestMapping("/quick11")
    @ResponseBody
    public void quickMethod11(@CookieValue(value = "JSESSIONID",required = false) String jsessionid){
        System.out.println(jsessionid);
    }
}
