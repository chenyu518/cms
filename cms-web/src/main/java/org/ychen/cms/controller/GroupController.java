package org.ychen.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ychen.basic.model.Pager;
import org.ychen.cms.dto.TreeDto;
import org.ychen.cms.model.ChannelTree;
import org.ychen.cms.model.Group;
import org.ychen.cms.model.User;
import org.ychen.cms.service.IGroupService;
import org.ychen.cms.service.IUserService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by cy on 16/12/14.
 */
@Controller
@RequestMapping("/admin/group")
public class GroupController {

    @Inject
    private IGroupService groupService;
    @Inject
    private IUserService userService;

    @RequestMapping("/groups")
    public String list(Model model){
        Pager<Group> groups = groupService.findGroup();
        model.addAttribute("datas",groups);
        return "group/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("group",new Group());
        return "group/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(@Valid Group group, BindingResult br, Model model){
        if(br.hasErrors()) {
            return "group/add";
        }
        groupService.add(group);
        return "redirect:/admin/group/groups";
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable int id,Model model){
        Group group = groupService.load(id);
        if(group!=null){
            model.addAttribute("group",group);
        }
        return "group/update";
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public String update(@PathVariable int id,@Valid Group group,BindingResult br, Model model){
        if(br.hasErrors()) {
            return "group/update";
        }
        Group g = groupService.load(id);
        g.setName(group.getName());
        g.setDescr(group.getDescr());
        groupService.update(g);
        return "redirect:/admin/group/groups";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id){
        groupService.delete(id);
        return "redirect:/admin/group/groups";
    }

    @RequestMapping(value = "/{id}")
    public String show(@PathVariable int id,Model model){
        model.addAttribute("group",groupService.load(id));
        model.addAttribute("us",userService.listGroupUsers(id));
        return "group/show";
    }

    @RequestMapping("/clearUsers/{id}")
    public String clearUsers(@PathVariable int id){
        groupService.deleteGroupUsers(id);
        return "redirect:/admin/group/groups";
    }

    @RequestMapping("/listChannels/{gid}")
    public String listChannels(@PathVariable int gid,Model model){
        model.addAttribute("gid",gid);
        model.addAttribute(groupService.load(gid));
        return "group/listChannel";

    }

    @RequestMapping("/groupTree/{gid}")
    @ResponseBody
    public List<ChannelTree>  groupChannelTree(@PathVariable Integer gid){
        List<ChannelTree> cts = groupService.generateGroupChannelTree(gid);
        return cts;
    }

}
