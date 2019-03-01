package com.qian.controller;
import com.qian.service.IDirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/dir")
@Scope("prototype")
public class DirAction {
    @Autowired
    IDirService iDirService;


    @RequestMapping("/findAllDirByUserId.do")
    @ResponseBody
    public Map findAllDirByUserId(String user_id) {
        List<Map> allDirByUserId = iDirService.findAllDirByUserId(user_id);
        Map map = new HashMap();
        map.put("msg", "success");
        map.put("code", 0);
        map.put("data", allDirByUserId);
        map.put("count", allDirByUserId.size());
        map.put("is", true);
        map.put("tip", "查询成功");
        return map;
    }
    //雷达图
    @RequestMapping("/radarAnalysis.do")
    @ResponseBody
    public List<Map> radarAnalysis(String user_id) {
        List<Map> maps = iDirService.radarAnalysis(user_id);
        System.out.println("雷达图"+maps);
        return maps;
    }
    //柱状图
    @RequestMapping("/pieAnalysis.do")
    @ResponseBody
    public List<Map> pieAnalysis(String user_id) {
        List<Map> maps = iDirService.pieAnalysis(user_id);
        System.out.println("柱状图"+maps);
        return maps;
    }

    //折线图
    @RequestMapping("/lineAnalysis.do")
    @ResponseBody
    public List<Map> lineAnalysis(String user_id) {
        List<Map> maps = iDirService.lineAnalysis(user_id);
        System.out.println("折线图"+maps);
        return maps;
    }


    @RequestMapping("/dirAnalysis.do")
    @ResponseBody
    public Map dirAnalysis(String user_id) {
        List<Map> maps = iDirService.dirAnalysis(user_id);
        System.out.println(maps);
        List newList = new ArrayList();
        Set set = new HashSet();
        for (Map list : maps) {
            set.add(list.get("dir_name"));
        }
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map map = new HashMap();
            map.put("name", iterator.next());
            List l = new ArrayList();
            for (Map m : maps) {
                if (map.containsValue(m.get("dir_name"))) {
                    Map mm = new HashMap();
                    mm.put("name", m.get("name"));
                    mm.put("value", 0);
                    l.add(mm);
                }
            }

            map.put("children", l);
            newList.add(map);
        }
        Map map1 = new HashMap();
        map1.put("name", "目录结构");
        map1.put("children", newList);
        return map1;
    }
}

