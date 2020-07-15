package com.bandg.uploadfiles.service;

import com.bandg.uploadfiles.dao.FileUpDao;
import com.bandg.uploadfiles.models.FileUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FileUpService {
    private final FileUpDao fileUpDao;

    @Autowired
    public FileUpService(FileUpDao fileUpDao) {
        this.fileUpDao = fileUpDao;
    }

    public int insertFile(UUID id, FileUp file) {

        return fileUpDao.insertFile(id,file);
    }
    public UUID insertFile( FileUp file) {


        return fileUpDao.insertFile(file);
    }
    public int delFileById(UUID id) {

        return fileUpDao.delFileById(id);
    }

    public FileUp getFileByID(UUID id) {
        return fileUpDao.getFileByID(id);
    }
}
