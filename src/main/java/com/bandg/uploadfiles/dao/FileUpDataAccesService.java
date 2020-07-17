package com.bandg.uploadfiles.dao;

import com.bandg.uploadfiles.models.FileUp;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository("postgres")
public class FileUpDataAccesService  implements FileUpDao{

    private  final JdbcTemplate jdbcTemplate;

    @Autowired
    public FileUpDataAccesService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UUID insertFile(UUID id, FileUp file) {

        FileUp fileUp = new FileUp(id,
                file.getFileName(),
                file.getPath(),
                file.getSize(),
                file.getOwnerName());
      try {
          String sql = "insert into FileUP(" +
                  "id," +
                  "filename," +
                  "path," +
                  "link," +
                  "f_size," +
                  "ownerName" +
                  ") values(?,?,?,?,?,?);";
          jdbcTemplate.update(sql,
                  fileUp.getId(),
                  fileUp.getFileName(),
                  fileUp.getPath(),
                  fileUp.getLink(),
                  fileUp.getSize(),
                  fileUp.getOwnerName()
          );
          return id;
      } catch (Exception e)
      {
          e.printStackTrace();
          return null;
      }
    }

    @Override
    public UUID insertFile(FileUp file) {
        UUID id  = UUID.randomUUID();
        try {
            while (getFileByID(id) != null)
                id = UUID.randomUUID();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return this.insertFile(id, file);
    }

    @Override
    public int delFileById(UUID id) {
        return 0;
    }

    @Override
    public FileUp getFileByID(UUID id) {
        String sql = "select * from FileUP where id='"+ id + "';" ;
       try {
           System.out.println("hello from getFileById");
            FileUp fileup =   jdbcTemplate.queryForObject(sql,(result, i) -> {
               FileUp file = new FileUp(
                       UUID.fromString(result.getString("id")),
                       result.getString("filename"),
                       result.getString("path"),
                       result.getDouble("f_size"),
                       result.getString("ownerName"),
                       result.getString("link")

               );

               System.out.println(file.toString());
                   return  file;

           });
            return fileup;
       }catch (Exception e) {
           e.printStackTrace();
       }
        return null;
    }
}
