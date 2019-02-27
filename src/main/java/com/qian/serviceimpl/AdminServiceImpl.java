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
    public List<Map> findAllUsers() {
        return adminDAO.findAllUsers();
    }
}
