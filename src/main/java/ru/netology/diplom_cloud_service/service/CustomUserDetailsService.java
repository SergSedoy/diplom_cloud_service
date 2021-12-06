//package ru.netology.diplom_cloud_service.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import ru.netology.diplom_cloud_service.pojo.User;
//import ru.netology.diplom_cloud_service.repository.CloudRepositoryImp;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final CloudRepositoryImp repository;
//
//    public CustomUserDetailsService(CloudRepositoryImp repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User myUser = repository.findByLogin(userName);
//        if (myUser == null) {
//            throw new UsernameNotFoundException("Unknown user: " + userName);
//        }
//        UserDetails user = User.builder()
//                .username(myUser.getLogin())
//                .password(myUser.getPassword())
//                .roles(myUser.getRole())
//                .build();
//        return user;
//    }
//}
