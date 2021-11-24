package ru.netology.diplom_cloud_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.exception.InputException;
import ru.netology.diplom_cloud_service.exception.UnauthorizedException;
import ru.netology.diplom_cloud_service.pojo.CloudFile;
import ru.netology.diplom_cloud_service.pojo.Token;
import ru.netology.diplom_cloud_service.pojo.User;
import ru.netology.diplom_cloud_service.repository.CloudRepositoryImp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CloudServiceImplTest {
    @InjectMocks
    CloudServiceImpl cloudServiceImpl;

    @Mock
    CloudRepositoryImp repository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login() {
        User user = new User("serg@mail.ru", "456", "Max","maxdb");
        List<User> list = Collections.singletonList(user);

        Mockito.when(repository.login(user)).thenReturn(list);

        User expected = cloudServiceImpl.login(user).getUser();
        User actual = repository.login(user).get(0);
        assertEquals(expected, actual);
    }

    @Test
    void loginTestWhenUserIsNull() {
        User user = null;
        assertThrows(UnauthorizedException.class, () -> cloudServiceImpl.login(user), "Bad credentials");
    }

    @Test
    void loginTestWhenUserFieldIsEmpty() {
        User user = new User("", "", "", "");
        UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> cloudServiceImpl.login(user));
        assertTrue(exception.getMessage().contains("Bad credentials"));
    }

    @Test
    void checkToken() {
        String authToken = null;
        UnauthorizedException unauthorizedException = assertThrows(UnauthorizedException.class, () -> cloudServiceImpl.checkToken(authToken));
        assertTrue(unauthorizedException.getMessage().contains("Unauthorized error"));
    }

    @Test
    void logout() {
        String expected = "Success logout";
        assertEquals(expected, cloudServiceImpl.logout());
    }

    @Test
    void uploadFile() {
        MultipartFile file = null;
        InputException exception = assertThrows(InputException.class, () -> cloudServiceImpl.uploadFile(file));
        assertTrue(exception.getMessage().contains("Error input data!"));
    }

    @Test
    void delFile() {
        String fileName = "";
        InputException exception = assertThrows(InputException.class, () -> cloudServiceImpl.delFile(fileName));
        assertTrue(exception.getMessage().contains("Error input data!"));
    }

    @Test
    void getFile() {
        String fileName = "test.txt";
        User user = new User("serg@mail.ru", "456", "Max","maxdb");
        List<User> list = Collections.singletonList(user);
        Resource resource = new UrlResource(getClass().getClassLoader().getResource("test.txt"));
        Mockito.when(repository.login(user)).thenReturn(list);
        Token token = cloudServiceImpl.login(user);
        Mockito.when(repository.getFile(fileName, token.getUser().getDtbase())).thenReturn(resource);

        Resource actual = cloudServiceImpl.getFile(fileName);
        Resource expected = resource;

        assertEquals(expected, actual);
    }

    @Test
    void getFileWhenFileNameIsNull() {
        String fileName = null;
        InputException exception = assertThrows(InputException.class, () -> cloudServiceImpl.getFile(fileName));
        assertTrue(exception.getMessage().contains("Error input data!"));
    }

    @Test
    void editFile() {
        String oldFileName = null, newFileName = null;
        InputException exception = assertThrows(InputException.class, () -> cloudServiceImpl.editFile(oldFileName, newFileName));
        assertTrue(exception.getMessage().contains("Error input data!"));
    }

    @Test
    void getListFile() {
        User user = new User("serg@mail.ru", "456", "Max", "maxdb");
        List<User> list = Collections.singletonList(user);
        Integer limit = 5;
        List<CloudFile> expectedList = new ArrayList<>();
        expectedList.add(new CloudFile("test.txt", 777777));

        Mockito.when(repository.login(user)).thenReturn(list);
        Token token = cloudServiceImpl.login(user);
        Mockito.when(repository.getListFile(limit, token.getUser().getDtbase())).thenReturn(expectedList);

        List<CloudFile> actualList = cloudServiceImpl.getListFile(limit);

        assertEquals(expectedList, actualList);
    }
}