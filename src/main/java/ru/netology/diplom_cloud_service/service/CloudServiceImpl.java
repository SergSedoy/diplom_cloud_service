package ru.netology.diplom_cloud_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.exception.InputException;
import ru.netology.diplom_cloud_service.exception.UnauthorizedException;
import ru.netology.diplom_cloud_service.pojo.Files;
import ru.netology.diplom_cloud_service.pojo.Token;
import ru.netology.diplom_cloud_service.pojo.User;
import ru.netology.diplom_cloud_service.repository.CloudRepositoryImp;

import java.io.File;
import java.util.List;

@Service
public class CloudServiceImpl implements CloudService {
    private final CloudRepositoryImp repository;
    private String token;


    public CloudServiceImpl(CloudRepositoryImp repository) {
        this.repository = repository;
    }

    public List<String> getListFile(Integer limit) {
        if (limit <= 0 || limit > 100)
            throw new InputException("Error input data!");
        return repository.getListFile(limit);
    }

    public String loging(User user) {
        List<User> list = repository.loging(user);
        for (User u : list) System.out.println(u);
        if (list.isEmpty())
            throw new UnauthorizedException("Bad credentials");
        token = new Token().getAuthToken();
        return token;
    }

    public void checkToken(String authToken) {
        if (authToken.isEmpty() || !authToken.equals(token))
            throw new UnauthorizedException("Unauthorized error");
        System.out.println("token проверен!");
    }

    @Override
    public void uploadFile(MultipartFile file) {
//        File convertFiles = new File("C:\\Users\\naste\\Desktop\\New\\" + file.getOriginalFilename());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getContentType());

        if (file.isEmpty())
            throw new InputException("Error input data");
        repository.uploadFile(file);
    }

    @Override
    public void delFile(String fileName) {

    }

    @Override
    public Files getFile(String fileName) {
        return null;
    }

    @Override
    public void editFile(String fileName) {

    }


}
