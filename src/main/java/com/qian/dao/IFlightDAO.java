package com.qian.dao;

import java.util.List;
import java.util.Map;

public interface IFlightDAO {
    List<Map<String,String>> findAllFlightInfo();
}
