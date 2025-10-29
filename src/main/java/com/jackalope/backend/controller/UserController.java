package com.jackalope.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.jackalope.backend.service.UserService;
import com.jackalope.backend.model.User;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // get single user by id
    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam(name = "userId") long userId) {
        // Implementation here
        User user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PatchMapping("/user")
    public ResponseEntity<User> updateUser(@RequestParam(name = "userId") long userId,
            @RequestBody User user) {
        User updateUser = userService.updateUser(userId, user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    // delete user by id
    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(@RequestParam(name = "userId") long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user_by_name")
    public List<User> findAllUserByFirstName(@RequestParam(name = "userFirstName") String userFirstName) {
        return userService.findAllUserByFirstName(userFirstName);
    }

}
