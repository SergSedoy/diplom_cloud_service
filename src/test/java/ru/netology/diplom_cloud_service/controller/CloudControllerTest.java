package ru.netology.diplom_cloud_service.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.netology.diplom_cloud_service.pojo.CloudFile;
import ru.netology.diplom_cloud_service.pojo.User;
import ru.netology.diplom_cloud_service.service.CloudServiceImpl;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CloudControllerTest {

    MockMvc mockMvc;
    ObjectMapper objectMapper;
    CloudServiceImpl cloudService;

    @BeforeEach
    void setUp() {
        cloudService = mock(CloudServiceImpl.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new CloudController(cloudService)).build();
        objectMapper  = new ObjectMapper();
    }

    @Test
    void delFile() throws Exception {
        mockMvc.perform(delete("/file")
                .param("filename", "test.txt")
                .header("auth-token", "Bearer auth-token #1"))
                .andExpect(status().isOk());
    }

    @Test
    void getFile() throws Exception {
        Resource resource = new UrlResource(getClass().getClassLoader().getResource("test.txt"));
        Mockito.when(cloudService.getFile("test.txt")).thenReturn(resource);
        mockMvc.perform(get("/file")
                .header("auth-token", "Bearer auth-token #1")
                .param("filename", "test.txt"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void editFile() throws Exception {
        String newFileName = "{\"filename\": \"789.txt\"}";

        mockMvc.perform(put("/file")
                .param("filename", "test.txt")
                .header("auth-token", "Bearer auth-token #1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newFileName)))
                .andExpect(status().isOk());
    }

    @Test
    void getListFile() throws Exception {
        List<CloudFile> list = Collections.singletonList(new CloudFile("test.txt", 7777));
        Mockito.when(cloudService.getListFile(3)).thenReturn(list);
        mockMvc.perform(get("/list")
                .header("auth-token", "Bearer auth-token #1")
                .param("limit", "3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }
}