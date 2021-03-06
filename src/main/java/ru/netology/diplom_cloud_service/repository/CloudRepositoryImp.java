package ru.netology.diplom_cloud_service.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.exception.InputException;
import ru.netology.diplom_cloud_service.exception.ServerException;
import ru.netology.diplom_cloud_service.pojo.CloudFile;
import ru.netology.diplom_cloud_service.pojo.User;

import javax.sql.DataSource;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Repository
public class CloudRepositoryImp implements CloudRepository {
    private static final Logger LOG = LoggerFactory.getLogger(CloudRepositoryImp.class);
    private final DataSource dataSource;

    @Autowired
    public CloudRepositoryImp(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User login(String login) {
        User userAuth = null;
        try (Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            String sql = String.format("SELECT * FROM user WHERE login = '%s'", login);
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                throw new InputException("Error input data", 400);
            }
            userAuth = new User(resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("name"), resultSet.getString("dtbase"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAuth;
    }

    @Override
    public void uploadFile(MultipartFile file, String dtBase) {

        try (Connection connection = dataSource.getConnection()) {
            String upload_date = new SimpleDateFormat("dd.MM.y k-mm").format(new GregorianCalendar().getTime());

            File tmpFile = File.createTempFile(upload_date, file.getOriginalFilename());
            file.transferTo(tmpFile);
            FileInputStream in = new FileInputStream(tmpFile);
            LOG.info("file save successful!");
            String sql = String.format("INSERT INTO %s (name, size, upload_date, content) VALUES (?, ?, ?, ?)", dtBase);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, file.getOriginalFilename());
            statement.setLong(2, file.getSize());
            statement.setString(3, upload_date);
            statement.setBinaryStream(4, in);
            statement.execute();
            in.close();
            tmpFile.delete();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delFile(String fileName, String dtBase) {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            String sql = String.format("DELETE FROM %s WHERE name = '%s'", dtBase, fileName);
            if (statement.execute(sql))
                throw new ServerException("Error delete file", 500);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resource getFile(String fileName, String dtBase) {
        String pathDir = "C:\\Users\\naste\\Desktop\\Tmp\\";
        for (File file : new File(pathDir).listFiles())
            if(file.isFile())
                file.delete();

        Resource resource = null;
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            String sql = String.format("SELECT * FROM %s WHERE name = '%s'", dtBase, fileName);
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                throw new InputException("Error input data", 400);
            }
            InputStream in = resultSet.getBinaryStream("content");
            File tmpFile = new File(pathDir + "target_" + fileName);
            FileOutputStream out = new FileOutputStream(tmpFile);
            byte[] buffer = new byte[8 * 1024];
            while (true) {
                int count = in.read(buffer);
                if (count < 0) {
                    break;
                }
                out.write(buffer, 0, count);
            }
            out.close();
            in.close();
            Path path = Paths.get(tmpFile.getAbsolutePath());
            resource = new UrlResource(path.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new ServerException("Error download file " + fileName, 500);
            }
        } catch (SQLException | IOException e) {
            throw new ServerException("Error upload file", 500);
        }
        return resource;
    }

    @Override
    public void editFile(String oldFileName, String newFileName, String dtBase) {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            String sql = String.format("UPDATE %s SET name = '%s' WHERE name = '%s'", dtBase, newFileName, oldFileName);
            statement.execute(sql);
        } catch (SQLException e) {
            throw new ServerException("Error upload file", 500);
        }

    }

    @Override
    public List<CloudFile> getListFile(Integer limit, String dtBase) {
        int count = 0;
        List<CloudFile> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM " + dtBase;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (count < limit) {
                    list.add(new CloudFile(resultSet.getString("name"), resultSet.getLong("size")));
                    count++;
                } else break;
            }
        } catch (SQLException e) {
            throw new ServerException("Error getting file list", 500);
        }
        return list;
    }


}
