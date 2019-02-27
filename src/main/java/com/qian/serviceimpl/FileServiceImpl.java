package com.qian.serviceimpl;
import com.qian.dao.IFileDAO;
import com.qian.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
@Service
public class FileServiceImpl implements IFileService{
    @Autowired
    IFileDAO fileDAOImpl;
    @Override
    public List<Map<String, Object>> findUserFile(Integer file_upload_user,String fileType,String isShare,Integer page,Integer limit) {
        return fileDAOImpl.findUserFile(file_upload_user,fileType,isShare,page,limit);
    }

    @Override
    public int updataFileName(Map map) {
        //System.out.println(map);
        int i = fileDAOImpl.updataFileName(map);
        return i;
    }

    @Override
    public int deleteFileById(List<String> list) {
        return fileDAOImpl.deleteFileById(list);
    }

    @Override
    public int updateFileStatus(Map map){
        return fileDAOImpl.updateFileStatus(map);
    }

    @Override
    public int updateDownloadCount(int file_id) {
        return  fileDAOImpl.updateDownloadCount(file_id);
    }
}
