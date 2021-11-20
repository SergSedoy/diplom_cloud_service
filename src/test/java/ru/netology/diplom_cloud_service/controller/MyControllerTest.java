package ru.netology.diplom_cloud_service.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.netology.diplom_cloud_service.pojo.User;
import ru.netology.diplom_cloud_service.service.CloudServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

class MyControllerTest {

    MockMvc mockMvc;
    ObjectMapper objectMapper;
    CloudServiceImpl cloudService;

    @BeforeEach
    void setUp() {
        cloudService = mock(CloudServiceImpl.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new MyController(cloudService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void login() {
    }

    @Test
    void logout() {
    }

    @Test
    void uploadFile() {
//        User user = new User("serg@mail.ru", "456", "maxdb");
        String fileName = "test.txt";
        byte[] content = new byte[5];
        MultipartFile file = new MockMultipartFile(fileName, content);
//        Mockito.when(cloudService.uploadFile(file)).getMock();

//        when(usersService.saveUser(any())).thenReturn(aUserDTO());
//        mockMvc.perform(post("/users/save")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(aUserDTO())))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(objectMapper.writeValueAsString(aUserDTO())));
    }

    @Test
    void delFile() {
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