package com.bandg.uploadfiles.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.minidev.json.JSONObject;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public class Person implements UserDetails {
    private  final UUID id;
    private  final String name;
    private  final String email;
    private final String password;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private  final Date dob;
    private String image;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Date getDob() {
        return dob;
    }

    public String getImage() {

        return this.image;
    }


    public Person(
            @JsonProperty("id") UUID id,
            @JsonProperty("name") @NonNull String name,
            @JsonProperty("email")@NonNull String email,
            @JsonProperty("pass") @NonNull String password,
            @JsonProperty("dob") @NonNull Date dob,
            @JsonProperty("image") @NonNull String image) {
       if (id == null)
           this.id = UUID.randomUUID();
        else
            this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.image = image;
    }
    public Person(
             UUID id,
            String name,
         String email,
           String password,
             Date dob){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.image = " ";
        
    }


}
