package com.bandg.uploadfiles.jwt;

import com.bandg.uploadfiles.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
public class PersonAuthenticationManager implements AuthenticationManager {
    private  final PersonService personService;
    private  final PasswordEncoder passwordEncoder;
    @Autowired
    public PersonAuthenticationManager(PersonService personService,  PasswordEncoder passwordEncoder1) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder1;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String)authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        UserDetails per;
        if(authentication.getCredentials() != null)
        if((per = personService.loadUserByUsername(email)) != null)
        {
            System.out.println("this is pass" + password);
            System.out.println(email);
            System.out.println(per.getPassword());
            System.out.println(per.getUsername());

            if(!passwordEncoder.matches(password, per.getPassword()))
                throw new  IllegalStateException("incorrect password");
        }
        else
            throw  new IllegalStateException("incorrect email");

        //  tPersonhrow new IOException();
        return authentication;
    }
}
