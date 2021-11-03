package ru.netology.diplom_cloud_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigApplicationContext implements WebMvcConfigurer{

    @Bean
    public CommonsMultipartResolver commonsMultipartResolver() {
        final CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(500000);
        return commonsMultipartResolver;
    }
//
//    @Bean
//    public FilterRegistrationBean multipartFilterRegistrationBean() {
//        final MultipartFilter multipartFilter = new MultipartFilter();
//        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(multipartFilter);
//        filterRegistrationBean.addInitParameter("multipartResolverBeanName", "commonsMultipartResolver");
//        return filterRegistrationBean;
//    }
}
