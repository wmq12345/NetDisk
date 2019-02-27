package com.qian.controller;

import com.alibaba.fastjson.JSON;
import com.qian.service.IFileService;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@Scope("prototype")
@RequestMapping("/file")
public class FileAction {
    @Autowired
    IFileService fileServiceImpl;
    @RequestMapping(value = "/updateFileStatus.do", method = RequestMethod.POST)
    @ResponseBody
    public int updateFileStatus(String file_status, String file_id) {
        Map map = new HashMap();
        map.put("file_id", file_id);
        map.put("file_status", file_status);
        int i = fileServiceImpl.updateFileStatus(map);
        return i;
    }

    @RequestMapping(value = "/findUserFile.do", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findUserFile(Integer file_upload_user,String fileType,String isShare,Integer page,Integer limit) {//
        List<Map<String, Object>> maps = fileServiceImpl.findUserFile(file_upload_user,fileType,isShare,page,limit);
        int count = Integer.parseInt(maps.get(maps.size()-1).get("count").toString());
        maps.remove(maps.size()-1);
        Map map = new HashMap();
        System.out.println(count);
        map.put("count",count);
        map.put("data",maps);
        if(maps!=null)
            map.put("msg","查询成功");
        else
            map.put("msg","查询未成功");
        map.put("code",0);
        return map;
    }
    @RequestMapping(value = "/updateFileName.do", method = RequestMethod.GET)
    @ResponseBody
    public int updateFileName(String obj){
        Map map = (Map) JSON.parse(obj);
        try {
            map.put("file_name",new String(map.get("file_name").toString().getBytes("iso-8859-1"),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // System.out.println(map);
        int i = fileServiceImpl.updataFileName(map);
        return i;
    }
    @RequestMapping(value = "/deleteFileById.do", method = RequestMethod.GET)
    @ResponseBody
    public int deleteFileById(String file_id){
        System.out.println(file_id);
        String[] split = file_id.substring(1,file_id.length()-1).split(",");
        List<String> strings = Arrays.asList(split);//把数组转换成列表
        //Map map = (Map) JSON.parse(file_id);
        int i = fileServiceImpl.deleteFileById(strings);
        return i;
    }

}
