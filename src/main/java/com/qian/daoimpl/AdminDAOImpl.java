package com.qian.daoimpl;

import com.qian.dao.IAdminDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("adminDAO")
public class AdminDAOImpl implements IAdminDAO {
    @Autowired
    SqlSessionFactory sqlSessionFactoryBean;
    @Override
    public List<Map> findAllUsers() {
        SqlSession sqlSession = sqlSessionFactoryBean.openSession(true);
        String sql = "com.qian.mapper.Admin.findAllUsers";
        List<Map> list = sqlSession.selectList(sql);
        return list;
    }
}
