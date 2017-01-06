package org.ychen.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ychen.cms.dao.IUserDao;
import org.ychen.cms.service.IUserService;

/**
 * Created by cy on 16/12/6.
 */
@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String index(Model model){
        return "admin/index";
    }
}
