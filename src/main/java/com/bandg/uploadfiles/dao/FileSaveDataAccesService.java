package com.bandg.uploadfiles.dao;

import com.bandg.uploadfiles.models.FileUp;
import com.bandg.uploadfiles.service.FileUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Stream;
@Repository
public class FileSaveDataAccesService  implements  FileSaveDao
{
    private final FileUpService fileUpDao;

    @Autowired
    public FileSaveDataAccesService(FileUpService fileUpDao) {
        this.fileUpDao = fileUpDao;
    }

    @Override
    public void init() {
        File dir = new File("/tmp/files");
        if(!dir.exists())
            dir.mkdirs();
    }

    @Override
    public UUID store(MultipartFile file) {
        FileUp fu = new FileUp(
                UUID.randomUUID(),
                file.getOriginalFilename(),
                "/tmp/files/" + file.getOriginalFilename(),
                file.getSize(),
                "bone"
        );
       UUID id = fileUpDao.insertFile(fu);
        try {
            file.transferTo(new File(fu.getPath()));
        } catch (IOException e)
        {
            e.printStackTrace();

        }
        return  id;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public FileUp getFileInfo(UUID id) {

        return fileUpDao.getFileByID(id);
    }

    @Override
    public Path load(String filename)
    {

        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
