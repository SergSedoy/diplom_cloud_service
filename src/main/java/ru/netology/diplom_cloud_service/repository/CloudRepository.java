package ru.netology.diplom_cloud_service.repository;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.pojo.CloudFile;
import ru.netology.diplom_cloud_service.pojo.User;

import java.util.List;

public interface CloudRepository {

    User login(String login);

    List<CloudFile> getListFile(Integer limit, String dtBase);

    void uploadFile(MultipartFile file, String dtBase);

    void delFile(String fileName, String dtBase);

    Resource getFile(String fileName, String dtBase);

    void editFile(String oldFileName, String newFileName, String dtBase);
}
