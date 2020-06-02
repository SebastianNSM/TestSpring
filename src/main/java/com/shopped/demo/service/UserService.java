package com.shopped.demo.service;

import com.shopped.demo.dao.UserDao;
import com.shopped.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("noSql") UserDao userDao) {
        this.userDao = userDao;
    }

    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public Optional<User> getUserById(UUID id) {
        return userDao.selectUserById(id);
    }

    public int deleteUser(UUID id) {
        return userDao.deleteUserById(id);
    }

    public int updateUser(UUID id, User newUser) {
        return userDao.updateUserById(id, newUser);
    }
}
