package com.qian.controller;
import com.qian.pojo.UserInfo;
import com.qian.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@Scope("prototype")
//@RequestMapping("/user")
public class UserAction {
    @Autowired
    IUserService userServiceImpl;
    @RequestMapping(value = "/updateOldUpwd.do", method = RequestMethod.GET)
    @ResponseBody
    public int updateOldUpwd(int userid,String oldpwd,String newpwd){
        int validate = userServiceImpl.validateOldPwd(userid,oldpwd);
        if (validate==0){
            return -1;
        }else{
            int i = userServiceImpl.updateOldPwd(userid,newpwd);
            return i;
        }
    }
    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    @ResponseBody
    public Map login(String uname, String upwd, HttpSession session) {
        Map<String, Object> login = userServiceImpl.login(uname, upwd);
        //System.out.println("action"+login);
        HashMap map=new HashMap();
        if(login==null){
            map.put("loginmsg",0);
            System.out.println("==null");
            return map;
        } else{
            map.put("loginmsg",1);
            map.put("userid",login.get("user_id").toString());
            map.put("isAdmin",login.get("isAdmin"));
            session.setAttribute("userid",login.get("user_id").toString());
            if(login.get("photo")!=null)
                map.put("photo",login.get("photo").toString());
            else
              map.put("photo","myphoto/myphoto.jpg");
            return map;
        }
    }
    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    @ResponseBody
    public int register(String uname, String upwd,String email) {
        Map map=new HashMap();
        map.put("uname",uname);
        map.put("upwd",upwd);
        map.put("email",email);
        int register = userServiceImpl.register(map);
        System.out.println(uname + "," + upwd + "," + register);
        if (register == 0) {
            return 0;
        } else {
            return 1;
        }
    }
    @RequestMapping(value = "/findUserInfoById.do", method = RequestMethod.POST)
    @ResponseBody
    public Map findUserInfoById(String user_id) {
        Map userInfoById = userServiceImpl.findUserInfoById(user_id);
        return userInfoById;
    }

    @RequestMapping(value = "/updateUserInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public int updateUserInfo(UserInfo userinfo){
        System.out.println(userinfo);
//        int i = userServiceImpl.updateUserInfo(userinfo);
//        return i;
        return 1;
    }

    @RequestMapping(value = "/userAnalysis.do", method = RequestMethod.GET)
    @ResponseBody
    public Map userStatusAnalysis() {
        List<Map> list = userServiceImpl.userStatusAnalysis();
        List<Integer> countList = new ArrayList<>();
        List<String> statusList = new ArrayList<>();
        for(Map map:list){
            statusList.add(map.get("status").toString());
            countList.add(Integer.parseInt(map.get("count").toString()));
        }
        Map map = new HashMap();
        map.put("status",statusList);
        map.put("count",countList);
        return map;
    }

}
