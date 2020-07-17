package com.bandg.uploadfiles.dao;


import com.bandg.uploadfiles.models.Person;

import java.util.UUID;

public interface PersonDao {
    int insertPerson(Person person);
    Person getPersonByID(UUID id);
    Person getPersonByEmail(String email);
    int deletePersonByID(UUID id);
}
