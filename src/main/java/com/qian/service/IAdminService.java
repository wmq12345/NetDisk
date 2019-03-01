package com.qian.service;

import java.util.List;
import java.util.Map;

public interface IAdminService {
    List<Map<String, Object>> findAllUsers(Integer page, Integer limit);
    int changeActiveStatus(Map map);
    int changeAdminStatus(Map map);
    int deleteFileById(List<String> strings);
}
