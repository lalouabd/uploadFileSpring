package com.bandg.uploadfiles.models;

import com.bandg.uploadfiles.dao.FileSaveDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class FileSaveService {
    private  final FileSaveDao fileSaveDao;
    @Autowired
    public FileSaveService(FileSaveDao fileSaveDao) {
        this.fileSaveDao = fileSaveDao;
    }
    public UUID store(MultipartFile file)
    {
        return fileSaveDao.store(file);
    }

    public void init() {
        fileSaveDao.init();
    }

    public FileUp getFileDetails(UUID id) {
        return  fileSaveDao.getFileInfo(id);
    }
}
