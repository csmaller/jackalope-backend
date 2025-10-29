package com.jackalope.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackalope.backend.model.User;
import com.jackalope.backend.repository.UserRepository;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setAbout(user.getAbout());
        existingUser.setEmail(user.getEmail());
        userRepository.save(existingUser);

        return existingUser;
    }

    public void deleteUser(long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        userRepository.delete(existingUser);
    }

    public List<User> findAllUserByFirstName(String userName) {
        return userRepository.findAllUserByFirstName(userName);
    }
}
