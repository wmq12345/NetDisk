package com.qian.serviceimpl;
import com.qian.dao.IAdminDAO;
import com.qian.dao.IUserDAO;
import com.qian.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    IAdminDAO adminDAO;
    @Override
    public List<Map<String, Object>> findAllUsers(Integer page, Integer limit) {
        return adminDAO.findAllUsers(page,limit);
    }

    @Override
    public int changeActiveStatus(Map map) {
        return adminDAO.changeActiveStatus(map);
    }

    @Override
    public int changeAdminStatus(Map map) {
        return adminDAO.changeAdminStatus(map);
    }

    @Override
    public int deleteFileById(List<String> strings) {
        return adminDAO.deleteFileById(strings);
    }

}
