package ru.itmentor.spring.boot_security.demo.creator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class CreateUser {

    private final UserService userService;
    @Autowired
    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void createUser() {
        User user = new User("admin", "admin", "Andrei", "Prishchepo", 33
                , "stalse@gmail.com", Arrays.asList("ROLE_ADMIN"));

        User user1 = new User("user", "user", "Andrei", "Prishchepo", 33
                , "stalse@gmail.com", Arrays.asList("ROLE_USER"));

        userService.saveUser(user);
        userService.saveUser(user1);
    }
}
