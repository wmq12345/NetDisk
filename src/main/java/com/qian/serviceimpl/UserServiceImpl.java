package com.qian.serviceimpl;
import com.qian.dao.IUserDAO;
import com.qian.pojo.UserInfo;
import com.qian.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class UserServiceImpl implements IUserService {
@Autowired
IUserDAO userDAO;
    @Override
    public  Map<String,Object> login(String uname, String upwd) {
        Map<String,Object> login = userDAO.login(uname, upwd);
        System.out.println("ser"+login);
        return login;
    }

    @Override
    public int register(Map<String, Object> obj) {
        int register = userDAO.register(obj);
        return register;
    }

    @Override
    public int validateOldPwd(int userid, String oldpwd) {
        int validate = userDAO.validateOldPwd(userid, oldpwd);
        return validate;
    }

    @Override
    public int updateOldPwd(int userid,String newpwd) {
        int validate = userDAO.updateOldPwd(userid,newpwd);
        return validate;
    }

    @Override
    public int updateUserPhoto(String userid, String photoPath) {
       int i = userDAO.updateUserPhoto(userid,photoPath);
        return i;
    }

    @Override
    public Map findUserInfoById(String user_id) {
        Map i = userDAO.findUserInfoById(user_id);
        return i;
    }

    @Override
    public int updateUserInfo(UserInfo userInfo) {
        int i = userDAO.updateUserInfo(userInfo);
        return i;
    }

    @Override
    public List<Map> userStatusAnalysis() {
        List<Map> list = userDAO.userStatusAnalysis();
        return list;
    }


}
