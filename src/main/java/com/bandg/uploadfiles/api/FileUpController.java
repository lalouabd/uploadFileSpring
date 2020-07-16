package com.bandg.uploadfiles.api;

import com.bandg.uploadfiles.service.FileSaveService;
import com.bandg.uploadfiles.models.FileUp;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.MultipartConfigElement;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

            method = RequestMethod.POST
    )
    public ResponseEntity<JSONObject>  handleFileUpload(@RequestParam(value= "file") MultipartFile file) {
        UUID id = null;
        try {

            id =  fileUpService.store(file);
            System.out.println(id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        JSONObject  res  = new JSONObject();
        res.put("id" , id.toString());
        //Access-Control-Allow-Origin:

        return ResponseEntity.ok()
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)
                .contentType(MediaType.APPLICATION_JSON).body(res);
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
    @RequestMapping(
            path = "/get/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("id") UUID id)  {

        String filename  = fileUpService.getFileDetails(id).getFileName();
        MediaType mediaType = MediaTypeFactory.getMediaType( filename).get();


        Path path = Paths.get(fileUpService.getFileDetails(id).getPath());
        try {
            byte[] data = Files.readAllBytes(path);
            ByteArrayResource resource = new ByteArrayResource(data);

            return ResponseEntity.ok()
                    // Content-Disposition
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                    // Content-Type
                    .contentType(mediaType) //
                    // Content-Lengh
                    .contentLength(data.length) //
                    .body(resource);
        }catch (Exception e)
        {
            return null;
        }
    }
//
    
    
}
