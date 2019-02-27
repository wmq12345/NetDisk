package com.qian.daoimpl;

import com.qian.dao.IFlightDAO;
import com.qian.service.IFlightService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class FlightInfoDAOImp implements IFlightDAO {
    @Autowired
    SqlSessionFactory sqlSessionFactoryBean;
    @Override
    public List<Map<String, String>> findAllFlightInfo() {
        System.out.println("dao test ok");
        SqlSession sqlSession = sqlSessionFactoryBean.openSession(true);
        String sql="com.qian.mapper.flight.findAllFlightInfo";
        List<Map<String, String>> objects = sqlSession.selectList(sql);
        return objects;
    }
}
