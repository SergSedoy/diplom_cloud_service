package ru.netology.diplom_cloud_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.diplom_cloud_service.pojo.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByLogin(String login);
}
