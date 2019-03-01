package com.qian.controller;

import com.qian.service.IDirService;
import com.qian.service.IFileService;
import com.qian.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@Controller
@Scope("prototype")
@RequestMapping("/file")
public class UploadAction {
    @Autowired
    IUserService userServiceImpl;
    @Autowired
    IFileService fileServiceImpl;
    @Autowired
    IDirService iDirService;
    @RequestMapping(value = "/uploadSelectFile.do", method = RequestMethod.POST)
    @ResponseBody
    public Map uploadSelectFile(@RequestParam("file") MultipartFile file,String file_type){
        return null;
    }
    @RequestMapping(value = "/downloadFile_1", method = RequestMethod.GET)
    @ResponseBody
    public String downloadFile(int file_id,String filePath, String fileName, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(filePath + "/" + fileName);
        //获取到真实路径
        String realPath = request.getSession().getServletContext().getRealPath("WEB-INF/file_resource") + filePath + "/" + fileName;
        try {
            realPath = new String(realPath.getBytes("iso-8859-1"), "utf-8");
            File file = new File(realPath);
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("iso-8859-1"), "utf-8"));
            response.setHeader("Content-Length", String.valueOf(file.length()));
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            byte[] size = new byte[1024];
            int length = 0;
            while ((length = bufferedInputStream.read(size)) != -1) {
                bufferedOutputStream.write(size, 0, length);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            bufferedInputStream.close();
            int i = fileServiceImpl.updateDownloadCount(file_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "/downloadFile.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity downloadFile(String filePath, String fileName, HttpServletRequest request) {
        //System.out.println(filePath+"/"+fileName);
        //获取到真实路径
        String realPath = request.getSession().getServletContext().getRealPath("WEB-INF/file_resource/") + filePath + "/" + fileName;
        System.out.println(realPath);        //
        try {
            FileInputStream fileInputStream = new FileInputStream(realPath);
            byte[] size = new byte[fileInputStream.available()];
            fileInputStream.read(size);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Disposition", "attachment;filename=" + fileName);
            HttpStatus httpStatus = HttpStatus.OK;
            ResponseEntity responseEntity = new ResponseEntity(size, httpHeaders, httpStatus);//构造相应实体
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/uploadPersonPhoto.do", method = RequestMethod.POST)
    @ResponseBody
    public Map uploadPersonPhoto(@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        String userid = session.getAttribute("userid").toString();
        System.out.println(userid);
        Map map = new HashMap();
        if (file != null) {
            String fileName = file.getOriginalFilename();
            String exName = fileName.substring(fileName.lastIndexOf("."));
            List list = new ArrayList();
            list.add(".jpg");
            list.add(".png");
            list.add(".gif");
            list.add(".jpeg");
            list.add(".bmp");
            if (list.contains(exName.toLowerCase())) {
                String photoFileName = new Date().getTime() + exName;
                String realPath = session.getServletContext().getRealPath("myphoto");
                String descPath = realPath + "/" + photoFileName;
                System.out.println(descPath);
                file.transferTo(new File(realPath, photoFileName));
                int i = userServiceImpl.updateUserPhoto(userid, "myphoto/" + photoFileName);
                if (i > 0) {
                    map.put("photo", "myphoto/" + photoFileName);
                    map.put("code", 0);//上传头像成功
                } else {
                    map.put("code", 3);//数据库存储不成功
                }
            } else {
                map.put("code", 2);//文件类型错误
            }
        } else {
            map.put("code", 5);//上传文件为空
        }
        return map;
    }
}
