package com.bandg.uploadfiles.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import  java.io.File;

public class Person {
    public final UUID id;
    public final String name;
    public final String email;
    public final String password;
    public final Date dob;
    public final JSONObject image;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getDob() {
        return dob;
    }

    public JSONObject getImage() {
        return image;
    }
    public  boolean handleAndSaveImage()
    {
        return false;
    }
    public Person(
            @JsonProperty("id") UUID id,
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("pass") String password,
            @JsonProperty("dob") Date dob,
            @JsonProperty("Image") JSONObject image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.image = image;
    }
}
