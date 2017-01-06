package org.ychen.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.ychen.basic.util.EnumUtils;
import org.ychen.cms.model.Role;
import org.ychen.cms.model.RoleType;
import org.ychen.cms.service.IRoleService;
import org.ychen.cms.service.IUserService;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * Created by cy on 16/12/13.
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {

    @Inject
    private IRoleService roleService;
    @Inject
    private IUserService userService;

    @RequestMapping("/roles")
    public String list(Model model){
        model.addAttribute("roles",roleService.listRole());
        return "role/list";
    }


    @RequestMapping("/{id}")
    public String show(@PathVariable int id,Model model) {
        model.addAttribute(roleService.load(id));
        model.addAttribute("us",userService.listRoleUsers(id));
        return "role/show";
    }

    @RequestMapping(value = "/add")
    public String add(Model model){
        model.addAttribute("role",new Role());
        model.addAttribute("types", EnumUtils.enum2Name(RoleType.class));
        return "role/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(@Valid Role role, BindingResult br, Model model){
        if (br.hasErrors()){
            return "role/add";
        }
        roleService.add(role);
        return "redirect:/admin/role/roles";
    }

    @RequestMapping(value = "/update/{id}")
    public String update(@PathVariable int id,Model model){
        model.addAttribute("role",roleService.load(id));
        model.addAttribute("types",EnumUtils.enum2Name(RoleType.class));
        return "role/update";
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public String update(@PathVariable int id, @Valid Role role, BindingResult br, Model model){
        if (br.hasErrors()){
            return "role/update";
        }
        Role r = roleService.load(id);
        r.setName(role.getName());
        r.setRoleType(role.getRoleType());
        roleService.update(r);
        return "redirect:/admin/role/roles";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id){
        roleService.delete(id);
        return "redirect:/admin/role/roles";
    }

    @RequestMapping(value = "/clearUsers/{id}")
    public String clearUsers(@PathVariable int id){
        roleService.deleteRoleUsers(id);
        return "redirect:/admin/role/roles";
    }




}
