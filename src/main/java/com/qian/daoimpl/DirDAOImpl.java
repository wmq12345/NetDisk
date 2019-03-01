package com.qian.daoimpl;

import com.qian.dao.IDirDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository("iDirDAO")
public class DirDAOImpl implements IDirDAO {
    @Autowired
    SqlSessionFactory sqlSessionFactoryBean;
    @Override
    public List<Map> findAllDir(String user_id) {
        SqlSession sqlSession = sqlSessionFactoryBean.openSession(true);
        String sql = "com.qian.mapper.Dir.finAllDir";
        List<Map> list= sqlSession.selectOne(sql,user_id);
        sqlSession.commit();
        return list;
    }

    @Override
    public List<Map> dirAnalysis(String user_id) {
        SqlSession sqlSession = sqlSessionFactoryBean.openSession(true);
        String sql = "com.qian.mapper.Dir.dirAnalysis";
        List<Map> list= sqlSession.selectList(sql,user_id);
        sqlSession.commit();
        return list;
    }

    @Override
    public Map findFilePathByDirId(String dir_id) {
//        SqlSession sqlSession = sqlSessionFactoryBean.openSession(true);
//        String sql = "com.qian.mapper.Dir.findFilePath";
//        Map list= sqlSession.selectList(sql,dir_id);
        return null;
    }
}
