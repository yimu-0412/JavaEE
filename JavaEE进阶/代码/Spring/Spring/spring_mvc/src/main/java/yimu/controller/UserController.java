package yimu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author wangyimu
 * @Program Spring
 * @create 2022-02-20-14:32
 */

@Controller
@RequestMapping("/user")
public class UserController {


    // @RequestMapping(value = "/quick",method = RequestMethod.GET,params = {"username"})
    @RequestMapping(value = "/quick")
    public String save(){
        System.out.println("controller save running····");
        return "success";
    }

}