package ru.netology.diplom_cloud_service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.netology.diplom_cloud_service.pojo.User;
import ru.netology.diplom_cloud_service.service.CloudServiceImpl;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final CloudServiceImpl cloudService;

    @Autowired
    public CustomUserDetailsService(CloudServiceImpl cloudService) {
        this.cloudService = cloudService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = cloudService.findByLogin(login);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }
}
