package com.bandg.uploadfiles.service;

import com.bandg.uploadfiles.dao.PersonDao;
import com.bandg.uploadfiles.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements UserDetailsService { 
    public final PersonDao personDao;

    @Autowired
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return personDao.getPersonByEmail(email);
    }

    public Person loadUserByemail(String email) throws UsernameNotFoundException {

        return personDao.getPersonByEmail(email);
    }
    public Person register(Person person) {

        if(personDao.insertPerson(person) == 1)
            return person;
        return null;
    }
}
