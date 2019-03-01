package com.qian.daoimpl;

import com.qian.dao.IAdminDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository("adminDAO")
public class AdminDAOImpl implements IAdminDAO {
    @Autowired
    SqlSessionFactory sqlSessionFactoryBean;
    @Override
    public List<Map<String, Object>> findAllUsers(Integer page, Integer limit) {
        try {
            SqlSession sqlSession = sqlSessionFactoryBean.openSession();
            String sql = "com.qian.mapper.Admin.findAllUsers";
            String sql_count = "com.qian.mapper.Admin.findRSCount";
            Map map = new HashMap();
            map.put("page", (page - 1) * limit);
            map.put("limit", limit);
            List<Map<String, Object>> objects = sqlSession.selectList(sql, map);
            Map<String, Object> rscount = sqlSession.selectOne(sql_count, map);
            objects.add(rscount);
            return objects;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int changeActiveStatus(Map map) {
        try {
            SqlSession sqlSession = sqlSessionFactoryBean.openSession();
            String sql = "com.qian.mapper.Admin.changeActiveStatus";
            int update = sqlSession.update(sql, map);
            return update;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int changeAdminStatus(Map map) {
        try {
            SqlSession sqlSession = sqlSessionFactoryBean.openSession();
            String sql = "com.qian.mapper.Admin.changeAdminStatus";
            int update = sqlSession.update(sql, map);
            return update;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteFileById( List<String> strings) {
        try {
            SqlSession sqlSession = sqlSessionFactoryBean.openSession();
            String sql = "com.qian.mapper.Admin.deleteUserById";
            int i = sqlSession.delete(sql, strings);
            sqlSession.commit(true);
            return i;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}
