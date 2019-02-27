package com.qian.controller;
import com.qian.service.IAdminService;
import com.qian.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;
@Controller
@Scope("prototype")
@RequestMapping("/admin")
public class AdminAction {
    @Autowired
    IAdminService adminServiceImpl;
    @RequestMapping(value = "/getAllUsers.do", method = RequestMethod.GET)
    @ResponseBody
    public List<Map> findAllUsers(){
        List<Map> allUsers = adminServiceImpl.findAllUsers();
        //System.out.println(allUsers);
        return allUsers;
    }
}
