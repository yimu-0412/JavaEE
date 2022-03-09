package controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author
 * @Program
 * @create 2022-03-03-21:03
 */
@Controller
public class TargetController {

    @RequestMapping("/test")
    public ModelAndView show(){
        System.out.println("目标资源执行····");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","itcase");
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
