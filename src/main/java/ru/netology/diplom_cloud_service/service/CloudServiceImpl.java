package ru.netology.diplom_cloud_service.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.exception.InputException;
import ru.netology.diplom_cloud_service.exception.UnauthorizedException;
import ru.netology.diplom_cloud_service.pojo.Token;
import ru.netology.diplom_cloud_service.pojo.User;
import ru.netology.diplom_cloud_service.repository.CloudRepositoryImp;

import java.util.List;

@Service
public class CloudServiceImpl implements CloudService {
    private final CloudRepositoryImp repository;
    private Token token;


    public CloudServiceImpl(CloudRepositoryImp repository) {
        this.repository = repository;
    }

    public String loging(User user) {
        if (user.getLogin().isEmpty() || user.getPassword().isEmpty())
            throw new UnauthorizedException("Bad credentials");
        List<User> list = repository.loging(user);
        if (list.isEmpty())
            throw new UnauthorizedException("Bad credentials");
        System.out.println(list.get(0));
        token = new Token(list.get(0));
        return token.getAuthToken();
    }

    public void checkToken(String authToken) {
        if (token == null || authToken.isEmpty() || !authToken.equals(token.getAuthToken()))
            throw new UnauthorizedException("Unauthorized error");
        System.out.println("token подтвержден!");
    }

    public String logout() {
        token = null;
        return "Success logout";
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
    public Resource getFile(String fileName) {
        if (fileName.isEmpty())
            throw new InputException("Error input data");
        return repository.getFile(fileName, token.getUser().getDtbase());
    }

    @Override
    public void editFile(String oldFileName, String newFileName) {
        if (oldFileName.isEmpty() | newFileName.isEmpty())
            throw new InputException("Error input data");
        repository.editFile(oldFileName, newFileName, token.getUser().getDtbase());
    }

    public List<String> getListFile(Integer limit) {

        if (limit <= 0 || limit > 50)
            throw new InputException("Error input data!");
        return repository.getListFile(limit, token.getUser().getDtbase());
    }
}
