package ru.netology.diplom_cloud_service.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CloudFile {
    @JsonProperty("filename")
    private String fileName;
    private long size;

    public CloudFile(String fileName, long size) {
        this.fileName = fileName;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }



}
