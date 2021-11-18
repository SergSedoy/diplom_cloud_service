package ru.netology.diplom_cloud_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.exception.InputException;
import ru.netology.diplom_cloud_service.exception.UnauthorizedException;
import ru.netology.diplom_cloud_service.pojo.Token;
import ru.netology.diplom_cloud_service.pojo.User;
import ru.netology.diplom_cloud_service.repository.CloudRepositoryImp;

import java.io.IOException;
import java.util.ArrayList;
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
        User user = new User("serg@mail.ru", "456", "maxdb");
        List<User> list = new ArrayList<>();
        list.add(user);

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
        User user = new User("", "", "");
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
    }

    @Test
    void editFile() {
    }

    @Test
    void getListFile() {
    }
}