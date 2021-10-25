package ru.netology.diplom_cloud_service.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.exception.UnauthorizedException;
import ru.netology.diplom_cloud_service.pojo.File;
import ru.netology.diplom_cloud_service.pojo.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Statement;
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

    public List<User> loging(User user) {

        return entityManager.createQuery("select s from User s where s.password = :password AND s.login = :login", User.class)
                .setParameter("password", user.getPassword())
                .setParameter("login", user.getLogin())
                .getResultList();
    }
}
