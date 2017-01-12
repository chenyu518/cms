package org.ychen.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ychen.cms.dto.ResponseData;
import org.ychen.cms.dto.RoleCommand;
import org.ychen.cms.model.Role;
import org.ychen.cms.model.Test;
import org.ychen.cms.service.IRoleService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cy on 17/1/6.
 */
@Controller
@RequestMapping
public class ExtController {

    public static void main(String[] args){

        Test t = new Test();
        System.out.println(t.result());

    }

    @Inject
    private IRoleService roleService;

    @RequestMapping("ext")
    public String toJsp(){
        return "extJsp/ext2";
    }


    @RequestMapping("listRole")
    @ResponseBody
    public ResponseData<RoleCommand> list(){
        List<Role> roles = roleService.listRole();
        List<RoleCommand> commands = new ArrayList<RoleCommand>();
        for (Role role : roles){
            RoleCommand command = new RoleCommand();
            command.setRoleName(role.getName());
            command.setInfo(role.getRoleType());
            commands.add(command);
        }
        return new ResponseData<RoleCommand>(commands);
    }

//    @RequestMapping("/loadInfo")
//    public void loadInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        String productId = request.getParameter("productId");
//        PrintWriter writer = response.getWriter();
//        if ("001".equals(productId)){
//            writer.println("{success:true,data:{'introduction':'success'}}");
//        }
//
//    }

}
