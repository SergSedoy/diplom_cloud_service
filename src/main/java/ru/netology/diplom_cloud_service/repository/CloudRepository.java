package ru.netology.diplom_cloud_service.repository;

import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.pojo.Files;

import java.util.List;

public interface CloudRepository {
    List<String> getListFile(Integer limit, String dtBase);

    void uploadFile(MultipartFile file, String dtBase);

    void delFile(String fileName, String dtBase);

    Files getFile(String fileName, String dtBase);

    void editFile(String fileName, String dtBase);
}
