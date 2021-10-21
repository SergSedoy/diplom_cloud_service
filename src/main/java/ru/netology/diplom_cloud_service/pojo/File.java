package ru.netology.diplom_cloud_service.pojo;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class File {
    private String fileName;
    private int size;
    private String fileType;
    private SimpleDateFormat date;

    public File(String fileName, int size, String fileType, SimpleDateFormat date) {
        this.fileName = fileName;
        this.size = size;
        this.fileType = fileType;
        this.date = date;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public SimpleDateFormat getDate() {
        return date;
    }

    public void setDate(SimpleDateFormat date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return size == file.size &&
                fileName.equals(file.fileName) &&
                fileType.equals(file.fileType) &&
                date.equals(file.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, size, fileType, date);
    }

    @Override
    public String toString() {
        return "File{" +
                "fileName='" + fileName + '\'' +
                ", size=" + size +
                ", fileType='" + fileType + '\'' +
                ", date=" + date +
                '}';
    }
}
