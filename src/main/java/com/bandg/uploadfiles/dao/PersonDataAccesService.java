package com.bandg.uploadfiles.dao;

import com.bandg.uploadfiles.models.Person;
import com.bandg.uploadfiles.service.FileSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
@Repository
public class PersonDataAccesService  implements  PersonDao{
    private  final JdbcTemplate jdbcTemplate;
    private  final PasswordEncoder passwordEncoder;
    private final FileSaveService fileSaveService;

    @Autowired
    public PersonDataAccesService(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder, FileSaveService fileSaveService) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
        this.fileSaveService = fileSaveService;
    }

    @Override
    public int insertPerson(Person person) {

       try{


           String sql = "insert into person(" +
                   "id," +
                   "name,"+
                   "email," +
                   "password," +
                   "dob," +
                   "image) values(?,?,?,?,?,?);";
           jdbcTemplate.update(sql,
                   person.getId(),
                   person.getName(),
                   person.getEmail(),
                   passwordEncoder.encode(person.getPassword()),
                   person.getDob(),
                   person.getImage());
    }catch(Exception es)
    {

        es.printStackTrace();
    }

        return 1;
    }

    @Override
    public Person getPersonByID(UUID id) {
        return null;
    }

    @Override
    public Person getPersonByEmail(String email) {
        Person per = null;
        try {
            per = jdbcTemplate.queryForObject("select * from person where email='" + email + "';",
                    (result, i) -> {

                        return new Person(
                                UUID.fromString(
                                        result.getString("id")),
                                        result.getString("name"),
                                        result.getString("email"),
                                        result.getString("password"),
                                        result.getDate("dob"),
                                        result.getString("image")
                                        );
                    });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return per;
    }

    @Override
    public int deletePersonByID(UUID id) {
        return 0;
    }
}
