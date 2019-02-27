package com.qian.service;

import java.util.List;
import java.util.Map;

public interface IAdminService {
    List<Map<String, Object>> findAllUsers(Integer page, Integer limit);
}
