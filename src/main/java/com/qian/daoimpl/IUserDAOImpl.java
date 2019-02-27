package com.qian.daoimpl;

import com.qian.dao.IUserDAO;
import com.qian.pojo.UserInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userDAO")
public class IUserDAOImpl implements IUserDAO {
    @Autowired
    SqlSessionFactory sqlSessionFactoryBean;
    @Override
    public Map<String,Object> login(String uname, String upwd) {
        try {
            SqlSession sqlSession = sqlSessionFactoryBean.openSession(true);
            String sql = "com.qian.mapper.User.login";
            Map map = new HashMap();
            map.put("uname", uname);
            map.put("upwd", upwd);
            System.out.println(map);
            Map<String,Object> o = sqlSession.selectOne(sql, map);
            return o;
        }catch (Exception E){
            E.printStackTrace();
        }
        return null;
    }

    @Override
    public int register(Map<String, Object> obj) {
        SqlSession sqlSession = sqlSessionFactoryBean.openSession(true);
        String sql = "com.qian.mapper.User.register";
        int insert = sqlSession.insert(sql, obj);
        return insert;
    }

    @Override
    public int validateOldPwd(int userid, String oldpwd) {
        SqlSession sqlSession = sqlSessionFactoryBean.openSession(true);
        String sql = "com.qian.mapper.User.validateOldPwd";
        Map map = new HashMap();
        map.put("userid", userid);
        map.put("oldpwd", oldpwd);
        System.out.println(map);
        Map<String,Object> o = sqlSession.selectOne(sql, map);
        System.out.println(o);
        if (o != null) {
            return 1;
        } else
            return 0;
    }

    @Override
    public int updateOldPwd(int userid,String newpwd) {
        SqlSession sqlSession = sqlSessionFactoryBean.openSession(true);
        String sql = "com.qian.mapper.User.updateOldPwd";
        Map map = new HashMap();
        map.put("userid", userid);
        map.put("newpwd", newpwd);
        int update = sqlSession.update(sql, map);
        System.out.println(update);
        return update;
    }

    @Override
    public int updateUserPhoto(String userid, String photoPath) {
        SqlSession sqlSession = sqlSessionFactoryBean.openSession(true);
        String sql = "com.qian.mapper.User.updateUserPhoto";
        Map map = new HashMap();
        map.put("userid", userid);
        map.put("photopath", photoPath);
        int update = sqlSession.update(sql, map);
        sqlSession.commit();
        return update;
    }

    @Override
    public Map findUserInfoById(String user_id) {
        SqlSession sqlSession = sqlSessionFactoryBean.openSession(true);
        String sql = "com.qian.mapper.User.findUserInfoById";
        Map<String,Object> o = sqlSession.selectOne(sql,user_id);
        sqlSession.commit();
        return o;
    }

    @Override
    public int updateUserInfo(UserInfo userInfo) {
        SqlSession sqlSession = sqlSessionFactoryBean.openSession(true);
        String sql = "com.qian.mapper.User.updateUserInfo";
        int update = sqlSession.update(sql,userInfo);
        sqlSession.commit();
        System.out.println("dao======"+userInfo);
        return update;
    }

    @Override
    public List<Map> userStatusAnalysis() {
        SqlSession sqlSession = sqlSessionFactoryBean.openSession(true);
        String sql = "com.qian.mapper.User.userStatusAnalysis";
        List<Map> o = sqlSession.selectList(sql);
        return o;
    }


}
