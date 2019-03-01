package com.qian.dao;

import java.util.List;
import java.util.Map;

public interface IAdminDAO {
    List<Map<String, Object>> findAllUsers(Integer page, Integer limit);
    int changeActiveStatus(Map map);
    int changeAdminStatus(Map map);
    int deleteFileById( List<String> strings);
}
