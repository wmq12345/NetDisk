package com.qian.dao;

import java.util.List;
import java.util.Map;

public interface IDirDAO {
    List<Map> findAllDir(String user_id);
    List<Map> dirAnalysis(String user_id);
    List<Map> radarAnalysis(String user_id);
    List<Map> pieAnalysis(String user_id);
    List<Map> lineAnalysis(String user_id);
}
