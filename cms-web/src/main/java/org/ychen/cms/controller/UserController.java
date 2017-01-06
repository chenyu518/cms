package org.ychen.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ychen.cms.dto.UserDto;
import org.ychen.cms.model.*;
import org.ychen.cms.service.IGroupService;
import org.ychen.cms.service.IRoleService;
import org.ychen.cms.service.IUserService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by cy on 16/12/7.
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Inject
    private IUserService userService;
    @Inject
    private IRoleService roleService;
    @Inject
    private IGroupService groupService;

    @RequestMapping("/users")
    public String list(Model model){
        model.addAttribute("datas",userService.findUser());
        return "user/list";
    }

    private void initAddUser(Model model) {
        model.addAttribute("roles",roleService.listRole());
        model.addAttribute("groups", groupService.listGroup());
    }

    @RequestMapping(value="/add",method=RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("userDto",new UserDto());//user,user
        initAddUser(model);
        return "user/add";
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String add(@Valid UserDto userDto,BindingResult br,Model model) {
        if(br.hasErrors()) {
            initAddUser(model);
            return "user/add";
        }
        userService.add(userDto.getUser(), userDto.getRoleIds(), userDto.getGroupIds());
        return "redirect:/admin/user/users";
    }

    @RequestMapping(value="/update/{id}",method=RequestMethod.GET)
    public String update(@PathVariable int id,Model model) {
        User user = userService.load(id);
        List<Integer> rids = userService.listUserRoleIds(id);
        List<Integer> gids = userService.listUserGroupIds(id);
        if (user!=null){
            model.addAttribute("userDto",new UserDto(user,rids,gids));//user,user
            initAddUser(model);
        }
        return "user/update";
    }

    @RequestMapping(value="/update/{id}",method=RequestMethod.POST)
    public String update(@PathVariable int id,@Valid UserDto userDto,BindingResult br,Model model) {
        if(br.hasErrors()) {
            initAddUser(model);
            return "user/update";
        }
        User user = userService.load(id);
        user.setNickname(userDto.getNickname());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setStatus(userDto.getStatus());
        userService.update(user, userDto.getRoleIds(), userDto.getGroupIds());
        return "redirect:/admin/user/users";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id){
        User user = userService.load(id);
        if(user!=null){
            userService.delete(id);
        }
        return "redirect:/admin/user/users";
    }

    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.GET)
    public String updateStatus(@PathVariable int id){
        userService.updateStatus(id);
        return "redirect:/admin/user/users";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id,Model model){
        User user = userService.load(id);
        List<Group> groups = userService.listUserGroups(id);
        List<Role> roles = userService.listUserRoles(id);
        if(user!=null){
            model.addAttribute(user);
            model.addAttribute("gs",groups);
            model.addAttribute("rs",roles);
        }
        return "user/show";
    }

    @RequestMapping("/listChannels/{uid}")
    public String listChannels(@PathVariable int uid,Model model){
        model.addAttribute(userService.load(uid));
        return "user/listChannel";

    }

    

    @RequestMapping("/userTree/{uid}")
    @ResponseBody
    public List<ChannelTree>  groupTree(@PathVariable Integer uid){
        List<ChannelTree> cts = groupService.generateUserChannelTree(uid);
        return cts;
    }

}
