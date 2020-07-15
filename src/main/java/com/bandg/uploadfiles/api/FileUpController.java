package com.bandg.uploadfiles.api;

import com.bandg.uploadfiles.models.FileSaveService;
import com.bandg.uploadfiles.models.FileUp;
import com.bandg.uploadfiles.service.FileUpService;
import com.sun.istack.internal.NotNull;
import jdk.internal.org.objectweb.asm.TypeReference;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.MultipartConfigElement;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/api/files")
public class FileUpController {
    private final FileSaveService fileUpService;
    private MultipartProperties multipartProperties;

    @Autowired
    public FileUpController(FileSaveService fileUpService) {
        this.fileUpService = fileUpService;
        fileUpService.init();
        multipartProperties = new MultipartProperties();
        multipartProperties.createMultipartConfig();
    }

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(100000000);
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        return commonsMultipartResolver;
    }
    @Bean
    @ConditionalOnMissingBean
            ({ MultipartConfigElement.class,
            CommonsMultipartResolver.class })
    public MultipartConfigElement multipartConfigElement() {
        return this.multipartProperties.createMultipartConfig();
    }
    @RequestMapping(

            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody String handleFileUpload(@RequestParam(value= "file") MultipartFile file) {
        UUID id = null;
        try {

            id =  fileUpService.store(file);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + id  + "!");
        JSONObject  res  = new JSONObject();
        res.put("id" , id.toString());
        return res.toString();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{fileId}"
    )

    public @ResponseBody FileUp getFileDetails(@PathVariable("fileId") UUID id)
    {
        return fileUpService.getFileDetails(id);
    }
    
//    public MultipartFile getFile(UUID id)
//    {
//
//    }
//
    
    
}
