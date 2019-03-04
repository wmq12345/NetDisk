package com.qian.controller;
import com.qian.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Arrays;
import java.util.HashMap;
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
            return null;
    public Map<String, Object> findAllUsers(Integer page, Integer limit){
        List<Map<String,Object>> allUsers = adminServiceImpl.findAllUsers(page,limit);
        int count = Integer.parseInt(allUsers.get(allUsers.size()-1).get("count").toString());
        allUsers.remove(allUsers.size()-1);
        Map map = new HashMap();
        map.put("count",count);
        map.put("data",allUsers);
        if(allUsers!=null)
            map.put("msg","成功");
        else
            map.put("msg","未成功");
        map.put("code",0);
        return map;
    }

    @RequestMapping(value = "/updateActiveStatus.do", method = RequestMethod.POST)
    @ResponseBody
    public int changeActiveStatus(String user_id, String status){
        Map map = new HashMap();
        map.put("user_id",user_id);
        map.put("status",status);
        int i = adminServiceImpl.changeActiveStatus(map);
        return i;
    }

    @RequestMapping(value = "/updateAdminStatus.do", method = RequestMethod.GET)
    @ResponseBody
    public int changeAdminStatus(String user_id, String isAdmin){
        Map map = new HashMap();
        map.put("user_id",user_id);
        map.put("isAdmin",isAdmin);
        int i = adminServiceImpl.changeAdminStatus(map);
        System.out.println("admin"+i);
        return i;
    }

    @RequestMapping(value = "/deleteUserById.do",method = RequestMethod.POST)
    @ResponseBody
    public int deleteUserById(String user_id){
        String[] split = user_id.substring(1,user_id.length()-1).split(",");
        List<String> strings = Arrays.asList(split);//把数组转换成列表
        System.out.println(strings);
        int a =adminServiceImpl.deleteFileById(strings);
        return a;
    }

    @RequestMapping(value = "/addUserInfo.do",method = RequestMethod.POST)
    @ResponseBody
    public int addUserInfo(String uname,String pwd,String role,String email){
        if(role.equals("admin")){
            role = "1";
        }else{
            role = "0";
        }
        Map map = new HashMap();
        map.put("uname",uname);
        map.put("pwd",pwd);
        map.put("role",role);
        map.put("email",email);
        System.out.println(map);
        int a=adminServiceImpl.addUserInfo(map);
        return a;
    }



    @RequestMapping(value = "/findUserById.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findUserById(String user_id){
       Map<String, Object> a =adminServiceImpl.findUserById(user_id);
        return a;
    }

    @RequestMapping(value = "/editUserById.do",method = RequestMethod.POST)
    @ResponseBody
    public int editUserById(String user_id,String uname,String pwd,String email,String isAdmin){
        Map map = new HashMap();
        map.put("user_id",user_id);
        map.put("uname",uname);
        map.put("pwd",pwd);
        map.put("email",email);
        map.put("isAdmin",isAdmin);
        System.out.println("action"+map);
       int a =adminServiceImpl.editUserById(map);
       return a;
    }

    @RequestMapping(value = "/searchUser.do",method = RequestMethod.GET)
    @ResponseBody
    public Map searchUser(String content){
        List<Map<String, Object>> allUsers = adminServiceImpl.searchUser(content);
        Map map = new HashMap();
        if(allUsers!=null) {
            map.put("data", allUsers);
            map.put("msg", "成功");
        }
        else
            map.put("msg","未成功");
        map.put("code",0);
        return map;
    }
}
