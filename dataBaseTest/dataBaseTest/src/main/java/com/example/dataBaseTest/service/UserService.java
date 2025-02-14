package com.example.dataBaseTest.service;

import com.example.dataBaseTest.dto.UsernameChangeDTO;
import com.example.dataBaseTest.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {


    public User findUserById(String id);

    User findUserByUsername(String username);

    void deleteUser(String id);

    User updateUser(String id, User newUser);

    User createUser(User newUser);

    List<User> findAllUsers();

}
