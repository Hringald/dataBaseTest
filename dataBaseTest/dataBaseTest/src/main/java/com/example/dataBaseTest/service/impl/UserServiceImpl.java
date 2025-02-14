package com.example.dataBaseTest.service.impl;

import com.example.dataBaseTest.entity.User;
import com.example.dataBaseTest.repository.UserRepo;
import com.example.dataBaseTest.service.UserService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findUserById(String id) {
        return userRepo.findById(Long.valueOf(id)).orElse(null);

    }

    @Override
    public User updateUser(String id, User newUser) {
        User user = userRepo.findById(Long.valueOf(id)).orElse(null);
        user.setUsername(newUser.getUsername());

        userRepo.save(user);
        return user;
    }

    @Override
    public void deleteUser(String id) {
        User currentUser = findUserById(id);
        ;

        if (currentUser != null) {
            this.userRepo.delete(currentUser);
        }

    }

    @Override
    public User createUser(User newUser) {
        return userRepo.save(newUser);
    }

    @Override
    public List<User> findAllUsers() {
        return this.userRepo.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return this.userRepo.findByUsername(username).orElse(null);
    }
}