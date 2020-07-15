package com.bandg.uploadfiles.dao;

import com.bandg.uploadfiles.models.FileUp;
import org.springframework.content.rest.StoreRestResource;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.util.UUID;
import java.util.stream.Stream;
@StoreRestResource(path="files")
public interface FileSaveDao {
    void init();

    UUID store(MultipartFile file);

    Stream<Path> loadAll();
    FileUp getFileInfo(UUID id);

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
}
