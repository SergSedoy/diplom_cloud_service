package ru.netology.diplom_cloud_service.repository;

import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.pojo.File;

import java.util.List;

public interface CloudRepository {
    List<String> getListFile(Integer limit);

    void uploadFile(MultipartFile file);

    void delFile(String fileName);

    File getFile(String fileName);

    void editFile(String fileName);
}
