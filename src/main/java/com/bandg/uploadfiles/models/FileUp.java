package com.bandg.uploadfiles.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.io.File;
import java.util.UUID;


public class FileUp {
    private  final UUID id;



    private final String fileName;
    private final String path;
    private  final String link;
    private  final File  file;
    private final double size;
    private final String ownerName;


    public FileUp(
            @JsonProperty("id") UUID id,
            @JsonProperty("name") String fileName,
            @JsonProperty("path") String path,
            @JsonProperty("size") double size,
            @JsonProperty("owner") String ownerName) {
        this.id = id;
        this.fileName = fileName;
        this.path = path;
        this.file = new File("path");
        this.size = size;
        this.ownerName = ownerName;
        link = genrateLink();
    }
    public FileUp(
            @JsonProperty("id") UUID id,
            @JsonProperty("name") String fileName,
            @JsonProperty("path") String path,
            @JsonProperty("size") double size,
            @JsonProperty("owner") String ownerName,
            @JsonProperty("link") String link) {
        this.id = id;
        this.fileName = fileName;
        this.path = path;
        this.file = new File("path");
        this.size = size;
        this.ownerName = ownerName;
        this.link = link;
    }
    public String genrateLink() {
        return "http://2dd0393492c7.ngrok.io/api/files/get/" + this.id;
    }

    public UUID getId() {
        return id;
    }

    public String getLink() {
        return link;
    }
    public String getFileName() {
        return fileName;
    }

    public String getPath() {
        return path;
    }

    public File getFile() {
        return file;
    }

    public double getSize() {
        return size;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public String toString() {
        return "FileUp{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", path='" + path + '\'' +
                ", link='" + link + '\'' +
                ", file=" + file +
                ", size=" + size +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
