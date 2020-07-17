package com.bandg.uploadfiles.dao;

import com.bandg.uploadfiles.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

public class PersonDataAccesService  implements  PersonDao{
    private  final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccesService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(Person person) {
        String sql = "insert into person()";

        return 0;
    }

    @Override
    public Person getPersonByID(UUID id) {
        return null;
    }

    @Override
    public Person getPersonByEmail(String email) {
        return null;
    }

    @Override
    public int deletePersonByID(UUID id) {
        return 0;
    }
}
