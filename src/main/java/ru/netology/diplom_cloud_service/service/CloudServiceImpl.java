package ru.netology.diplom_cloud_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.exception.InputException;
import ru.netology.diplom_cloud_service.exception.UnauthorizedException;
import ru.netology.diplom_cloud_service.pojo.CloudFile;
import ru.netology.diplom_cloud_service.pojo.Token;
import ru.netology.diplom_cloud_service.pojo.User;
import ru.netology.diplom_cloud_service.repository.CloudRepositoryImp;

import java.util.List;

@Service
public class CloudServiceImpl implements CloudService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CloudServiceImpl.class);
    private final CloudRepositoryImp repository;
    private Token token;


    public CloudServiceImpl(CloudRepositoryImp repository) {
        this.repository = repository;
    }

    public Token login(User user) {
        if (user == null || user.getLogin().isEmpty() || user.getPassword().isEmpty())
            throw new UnauthorizedException("Bad credentials", 400);
        User userAuth  = repository.login(user);
        if (userAuth == null)
            throw new UnauthorizedException("Bad credentials", 400);
        LOGGER.info("достали из БД юзера {} для генерирования токена!", userAuth);
        token = new Token(userAuth);
        return token;
    }

    public void checkToken(String authToken) {
        if (token == null || authToken.isEmpty() || !authToken.equals("Bearer " + token.getAuthToken()))
            throw new UnauthorizedException("Unauthorized error", 401);
        LOGGER.info("token подтвержден!");
    }

    public String logout() {
        token = null;
        return "Success logout";
    }

    @Override
    public void uploadFile(MultipartFile file) {

        if (file == null || file.isEmpty())
            throw new InputException("Error input data!", 400);

        repository.uploadFile(file, token.getUser().getDtbase());
    }

    @Override
    public void delFile(String fileName) {
        if (fileName == null || fileName.isEmpty())
            throw new InputException("Error input data!", 400);
        repository.delFile(fileName, token.getUser().getDtbase());
    }

    @Override
    public Resource getFile(String fileName) {
        if (fileName == null || fileName.isEmpty())
            throw new InputException("Error input data!", 400);
        return repository.getFile(fileName, token.getUser().getDtbase());
    }

    @Override
    public void editFile(String oldFileName, String newFileName) {
        if (oldFileName == null || newFileName == null || oldFileName.isEmpty() || newFileName.isEmpty())
            throw new InputException("Error input data!", 400);
        repository.editFile(oldFileName, newFileName, token.getUser().getDtbase());
    }

    public List<CloudFile> getListFile(Integer limit) {

        if (limit <= 0 || limit > 20)
            throw new InputException("Error input data!", 400);
        return repository.getListFile(limit, token.getUser().getDtbase());
    }
}
