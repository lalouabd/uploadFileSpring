package com.bandg.uploadfiles.dao;

import com.bandg.uploadfiles.models.FileUp;

import java.util.UUID;

public interface FileUpDao {
    UUID insertFile(UUID id, FileUp file);
    UUID insertFile(FileUp file);

    int delFileById(UUID id);
    FileUp getFileByID(UUID id);


}
