package ru.netology.diplom_cloud_service.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.pojo.Files;

public interface CloudService {

    void uploadFile(MultipartFile file);

    void delFile(String fileName);

    Resource getFile(String fileName);

    void editFile(String fileName);
}
