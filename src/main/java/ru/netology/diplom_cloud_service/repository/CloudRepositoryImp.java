package ru.netology.diplom_cloud_service.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.exception.UnauthorizedException;
import ru.netology.diplom_cloud_service.pojo.File;
import ru.netology.diplom_cloud_service.pojo.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CloudRepositoryImp implements CloudRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> getListFile(Integer limit) {
        List<String> list = List.of("file 1", "file 2", "file 3");
        return list;
    }

    @Override
    public void uploadFile(MultipartFile file) {

    }

    @Override
    public void delFile(String fileName) {

    }

    @Override
    public File getFile(String fileName) {
        return null;
    }

    @Override
    public void editFile(String fileName) {

    }

    public String getToken(String auth_token) {
//        String sql = "select token from users where password = 123";
//        System.out.println(entityManager.createQuery(sql));
        return null;
    }
}
