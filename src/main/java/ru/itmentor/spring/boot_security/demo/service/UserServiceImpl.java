package ru.itmentor.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.dao.UserDao;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public void editUser(Long id, User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.editUser(id, user);
    }

    @Transactional
    @Override
    public void userForUpdate(User user, Long id) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.userForUpdate(user, id);
    }

    public User getUser(Long id) {
        return userDao.getUser(id);
    }


    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

}