package ru.netology.diplom_cloud_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.exception.InputException;
import ru.netology.diplom_cloud_service.exception.UnauthorizedException;
import ru.netology.diplom_cloud_service.pojo.Token;
import ru.netology.diplom_cloud_service.pojo.User;
import ru.netology.diplom_cloud_service.repository.CloudRepositoryImp;

import java.io.File;
import java.util.List;

@Service
public class CloudServiceImpl implements CloudService {
    private final CloudRepositoryImp repository;
    private Token token;


    public CloudServiceImpl(CloudRepositoryImp repository) {
        this.repository = repository;
    }

    public List<String> getListFile(Integer limit) {

        if (limit <= 0 || limit > 100)
            throw new InputException("Error input data!");
        return repository.getListFile(limit, token.getUser().getDtbase());
    }

    public String loging(User user) {
        List<User> list = repository.loging(user);
        if (list.isEmpty())
            throw new UnauthorizedException("Bad credentials");
        System.out.println(list.get(0));
        token = new Token(list.get(0));
        return token.getAuthToken();
    }

    public void checkToken(String authToken) {
        if (authToken.isEmpty() || !authToken.equals(token.getAuthToken()))
            throw new UnauthorizedException("Unauthorized error");
        System.out.println("token подтвержден!");
    }

    @Override
    public void uploadFile(MultipartFile file) {

        if (file.isEmpty())
            throw new InputException("Error input data");

        repository.uploadFile(file, token.getUser().getDtbase());
    }

    @Override
    public void delFile(String fileName) {
        if (fileName.isEmpty())
            throw new InputException("Error input data");
        repository.delFile(fileName, token.getUser().getDtbase());
    }

    @Override
    public File getFile(String fileName) {
        return null;
    }

    @Override
    public void editFile(String fileName) {

    }


}
