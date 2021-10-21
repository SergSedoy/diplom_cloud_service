package ru.netology.diplom_cloud_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.exception.InputException;
import ru.netology.diplom_cloud_service.repository.CloudRepositoryImp;

import java.util.List;

@Service
public class CloudServiceImpl implements CloudService {
    private final CloudRepositoryImp repository;

    public CloudServiceImpl(CloudRepositoryImp repository) {
        this.repository = repository;
    }

    public List<String> getListFile(Integer limit) {
        if (limit <= 0 || limit > 100)
            throw new InputException("Error input data!");
        return repository.getListFile(limit);
    }

    @Override
    public void uploadFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        repository.uploadFile(file);
    }
}
