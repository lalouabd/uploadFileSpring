package com.bandg.uploadfiles.dao;

import com.bandg.uploadfiles.models.FileUp;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.nio.file.OpenOption;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FakeFileUpDataAcces implements FileUpDao{
    private  final List<FileUp> files = new ArrayList<>();
    @Override
    public UUID insertFile(UUID id, FileUp file) {
       FileUp fileUp = new FileUp(id,
               file.getFileName(),
               file.getPath(),
               file.getSize(),
               file.getOwnerName());
       files.add(fileUp);
        return fileUp.getId();
    }
    @Override
    public UUID insertFile(FileUp file) {
        UUID id = UUID.randomUUID();
        boolean fi = false;
        while (fi) {
            UUID finalId = id;
            if(!files.stream().filter(p->p.getId().equals(finalId)).findFirst().isPresent())
                break;
            id = UUID.randomUUID();
        }
            FileUp fileUp = new FileUp(id,
                file.getFileName(),
                file.getPath(),
                file.getSize(),
                file.getOwnerName());
        files.add(fileUp);
        return id;
    }

    @Override
    public int delFileById(UUID id) {
        Optional<FileUp> op =files
                .stream()
                .filter(p-> p.getId().equals(id))
                .findFirst();
        if(op.isPresent())
        {
            File torem = new File(op.get().getPath());
            if(torem.exists())
                torem.delete();
            files.remove(op.get());
            return  1;
        }

        return 0;
    }

    @Override
    public FileUp getFileByID(UUID id) {
        return files
                .stream()
                .filter(p-> p.getId().equals(id))
                .findFirst().orElse(null);
    }
}
