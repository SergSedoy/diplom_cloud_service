package ru.netology.diplom_cloud_service.repository;

import com.mysql.cj.MysqlConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.pojo.File;
import ru.netology.diplom_cloud_service.pojo.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class CloudRepositoryImp implements CloudRepository {

    @Autowired
    private DataSource dataSource;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> getListFile(Integer limit) {
        List<String> list = List.of("file 1", "file 2", "file 3");
        return list;
    }

    @Override
    public void uploadFile(MultipartFile file) {
        try(Connection connection = dataSource.getConnection()) {

            String name = file.getOriginalFilename();
            String file_type = file.getContentType();
            long size = file.getSize();
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            Date upload_date = new Date();
            PreparedStatement statement = connection.prepareStatement("INSERT datefiles (name, file_type, size, upload_date) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, file_type);
            statement.setLong(3, size);
            statement.setDate(4, (java.sql.Date) upload_date);

        }catch (SQLException e) {
            e.getSQLState();
        }
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

        return entityManager.createQuery("SELECT s FROM User s WHERE s.password = :password AND s.login = :login", User.class)
                .setParameter("password", user.getPassword())
                .setParameter("login", user.getLogin())
                .getResultList();
    }
}
