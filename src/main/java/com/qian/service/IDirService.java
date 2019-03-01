package com.qian.service;

import java.util.List;
import java.util.Map;

public interface IDirService {
    List<Map> findAllDirByUserId(String user_id);
    List<Map> dirAnalysis(String user_id);
    List<Map> radarAnalysis(String user_id);
}
