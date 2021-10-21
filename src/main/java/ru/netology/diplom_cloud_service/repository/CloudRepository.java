package ru.netology.diplom_cloud_service.repository;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CloudRepository {
    List<String> getListFile(Integer limit);

    void uploadFile(MultipartFile file);
}
