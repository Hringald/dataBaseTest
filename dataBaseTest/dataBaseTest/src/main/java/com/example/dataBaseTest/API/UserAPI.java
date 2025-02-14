package com.example.dataBaseTest.API;

import com.example.dataBaseTest.entity.User;
import com.example.dataBaseTest.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserAPI {

    private final UserServiceImpl userService;

    @Autowired
    public UserAPI(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getUsers() {

        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        User user = userService.findUserById(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser, HttpServletRequest request) {
        User user = userService.createUser(newUser);
        if (user != null) {
            URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();
            return ResponseEntity.created(location).body(user);
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating user");
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User newUser, HttpServletRequest request) {
        User user = userService.updateUser(id, newUser);
        if (user != null && newUser.getUsername() != null) {
            URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();
            return ResponseEntity.created(location).body(user);
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating user");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}