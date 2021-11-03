package ru.netology.diplom_cloud_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication (exclude = {MultipartAutoConfiguration.class})
public class DiplomCloudServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiplomCloudServiceApplication.class, args);
    }

}
