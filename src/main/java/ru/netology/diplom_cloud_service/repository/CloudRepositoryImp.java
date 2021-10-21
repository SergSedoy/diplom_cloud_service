package ru.netology.diplom_cloud_service.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public class CloudRepositoryImp implements CloudRepository {

    @Override
    public List<String> getListFile(Integer limit) {
        List<String> list = List.of("file 1", "file 2", "file 3");
        return list;
    }

    @Override
    public void uploadFile(MultipartFile file) {

    }
}
