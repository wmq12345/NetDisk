package com.qian.daoimpl;

import com.qian.dao.IFileDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FileDAOImpl implements IFileDAO {
    @Autowired
    SqlSessionFactory sqlSessionFactoryBean;

    @Override
    public List<Map<String, Object>> findUserFile(Integer file_upload_user,String fileType,String isShare,Integer page, Integer limit) {
        try {
        SqlSession sqlSession = sqlSessionFactoryBean.openSession();
        String sql = "com.qian.mapper.File.findUserFile";
        String sql_count = "com.qian.mapper.File.findRSCount";
        Map map = new HashMap();
        map.put("user_id", file_upload_user);
        map.put("page", (page - 1) * limit);
        map.put("limit", limit);
        map.put("fileType",fileType);
        map.put("isShare",isShare);
            System.out.println(map);
        List<Map<String, Object>> objects = sqlSession.selectList(sql, map);
            System.out.println(objects);
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
    public int updataFileName(Map map) {
        SqlSession sqlSession = sqlSessionFactoryBean.openSession(true);
        String sql = "com.qian.mapper.File.updateFileName";
        int i = sqlSession.update(sql, map);
        sqlSession.commit(true);
        return i;
    }

    @Override
    public int deleteFileById(List<String> list) {
        SqlSession sqlSession = sqlSessionFactoryBean.openSession(true);
        String sql = "com.qian.mapper.File.deleteFileById";
        Map map = new HashMap();
        int i = 0;
        for (String str : list) {
            map.put("file_id",str);
            i = sqlSession.update(sql, map);
            sqlSession.commit(true);
        }
        System.out.println(map);
        return i;
    }

    @Override
    public int updateFileStatus(Map map) {
        SqlSession sqlSession = sqlSessionFactoryBean.openSession(true);
        String sql = "com.qian.mapper.File.updateFileStatus";
        int i = sqlSession.update(sql, map);
        sqlSession.commit(true);
        return i;
    }

    @Override
    public int updateDownloadCount(int file_id) {
        SqlSession sqlSession = sqlSessionFactoryBean.openSession(true);
        String sql = "com.qian.mapper.File.updateDownloadCount";
        int i = sqlSession.update(sql, file_id);
        sqlSession.commit(true);
        return i;
    }
}
