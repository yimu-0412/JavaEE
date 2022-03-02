package controller;

import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.RoleService;

import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-01-20:49
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /*public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }*/

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();
        // 设置模型
        modelAndView.addObject("roleList",roleList);
        // 设置视图
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    @RequestMapping(value = "/save",method=RequestMethod.POST )
    public String save(Role role){
        roleService.save(role);
        return "redirect:/role/list";
    }

    @RequestMapping(value = "/del/{roleId}")
    public String del(@PathVariable("roleId") Long roleId){
        roleService.del(roleId);
        return "redirect:/role/list";
    }
}
