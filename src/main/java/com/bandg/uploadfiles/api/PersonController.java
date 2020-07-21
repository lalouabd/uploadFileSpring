package com.bandg.uploadfiles.api;

import com.bandg.uploadfiles.dao.FileUpDao;
import com.bandg.uploadfiles.models.FileUp;
import com.bandg.uploadfiles.models.Person;
import com.bandg.uploadfiles.service.FileSaveService;
import com.bandg.uploadfiles.service.PersonService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController("/auth")
public class PersonController {
    private  final PersonService personService;
    private final PasswordEncoder passwordEncoder;
    private final FileSaveService fileSaveService;
    private  final FileUpDao fileUpDao;
    @Autowired
    public PersonController(PersonService personService,
                            PasswordEncoder passwordEncoder,
                            FileSaveService fileSaveService,
                            @Qualifier("postgres") FileUpDao fileUpDao) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
        this.fileSaveService = fileSaveService;
        this.fileUpDao = fileUpDao;
    }
    
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/auth/register"
    )
    public @ResponseBody  String  register(@RequestBody Person person)
    {

          personService.register(person);

           return  "ok";
    }
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/auth/me"
    )
    public String getInfo(Authentication aut){
        Person per  = personService.loadUserByemail(
                (String)aut.getPrincipal());
        JSONObject js  = new JSONObject();

        js.put("email",per.getEmail());
        js.put("name", per.getName());
        js.put("id", per.getId());
        js.put("imglink", "https://0a19182aa9f9.ngrok.io/api/files/" + per.getImage());
        js.put("dob", per.getDob());

        return js.toString();
    }



}
