package ru.netology.diplom_cloud_service.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudService {

    void uploadFile(MultipartFile file);
}
