package ru.netology.diplom_cloud_service.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.pojo.CloudFile;

import java.util.List;

public interface CloudService {

    void uploadFile(MultipartFile file);

    void delFile(String fileName);

    Resource getFile(String fileName);

    void editFile(String oldFileName, String newFileName);

    List<CloudFile> getListFile(Integer limit);
}
