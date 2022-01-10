package ru.netology.diplom_cloud_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.exception.InputException;
import ru.netology.diplom_cloud_service.exception.UnauthorizedException;
import ru.netology.diplom_cloud_service.pojo.CloudFile;
import ru.netology.diplom_cloud_service.pojo.User;
import ru.netology.diplom_cloud_service.repository.CloudRepository;
import ru.netology.diplom_cloud_service.repository.UserRepository;
import ru.netology.diplom_cloud_service.security.JwtProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CloudServiceImpl implements CloudService {

    private static final Logger LOG = LoggerFactory.getLogger(CloudServiceImpl.class);
    private final CloudRepository repository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;


    public CloudServiceImpl(CloudRepository repository, UserRepository userRepository, JwtProvider jwtProvider) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public Map<String, String> login(User user) {
        if (user == null || user.getLogin().isEmpty() || user.getPassword().isEmpty())
            throw new UnauthorizedException("Bad credentials", 400);
        User userAuth  = repository.login(user.getLogin());
        if (userAuth == null)
            throw new UnauthorizedException("Bad credentials", 400);
        LOG.info("нашли в БД юзера {} для генерирования токена!", userAuth);

        Map<String, String> mapa = new HashMap<>();
        mapa.put("auth-token", jwtProvider.generateToken(userAuth.getLogin()));
        LOG.info("токен = " + mapa.get("auth-token"));
        return mapa;
    }

    public String logout() {
        return "Success logout";
    }

    @Override
    public void uploadFile(MultipartFile file) {

        if (file == null || file.isEmpty())
            throw new InputException("Error input data!", 400);

        repository.uploadFile(file, findByLogin(getLoginFromUserDetails()).getDtbase());
    }

    @Override
    public void delFile(String fileName) {
        if (fileName == null || fileName.isEmpty())
            throw new InputException("Error input data!", 400);
        repository.delFile(fileName, findByLogin(getLoginFromUserDetails()).getDtbase());
    }

    @Override
    public Resource getFile(String fileName) {
        if (fileName == null || fileName.isEmpty())
            throw new InputException("Error input data!", 400);
        return repository.getFile(fileName, findByLogin(getLoginFromUserDetails()).getDtbase());
    }

    @Override
    public void editFile(String oldFileName, String newFileName) {
        if (oldFileName == null || newFileName == null || oldFileName.isEmpty() || newFileName.isEmpty())
            throw new InputException("Error input data!", 400);
        repository.editFile(oldFileName, newFileName, findByLogin(getLoginFromUserDetails()).getDtbase());
    }

    public List<CloudFile> getListFile(Integer limit) {

        if (limit <= 0 || limit > 20)
            throw new InputException("Error input data!", 400);
        return repository.getListFile(limit, findByLogin(getLoginFromUserDetails()).getDtbase());
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public String getLoginFromUserDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
