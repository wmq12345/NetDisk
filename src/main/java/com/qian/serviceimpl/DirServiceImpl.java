package com.qian.serviceimpl;

import com.qian.dao.IDirDAO;
import com.qian.service.IDirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class DirServiceImpl implements IDirService {
    @Autowired
    IDirDAO iDirDAO;
    @Override
    public List<Map> findAllDirByUserId(String user_id) {
        List<Map> allDir = iDirDAO.findAllDir(user_id);
        return allDir;
    }

    @Override
    public List<Map> dirAnalysis(String user_id) {
        List<Map> allDir = iDirDAO.dirAnalysis(user_id);
        return allDir;
    }

    @Override
    public List<Map> radarAnalysis(String user_id) {
        List<Map> allDir = iDirDAO.radarAnalysis(user_id);
        return allDir;
    }
//这是柱状图
    @Override
    public List<Map>pieAnalysis(String user_id) {
        List<Map> allDir = iDirDAO.pieAnalysis(user_id);
        return allDir;
    }

    @Override
    public List<Map> lineAnalysis(String user_id) {
        List<Map> allDir = iDirDAO.lineAnalysis(user_id);
        return allDir;
    }


}
