package com.jackalope.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jackalope.backend.model.User;
import com.jackalope.backend.model.LoginRequest;
import com.jackalope.backend.model.AuthResponse;
import com.jackalope.backend.repository.UserRepository;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public AuthResponse login(LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());
        
        if (userOpt.isEmpty()) {
            return new AuthResponse("User not found", null, false);
        }
        
        User user = userOpt.get();
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return new AuthResponse("Invalid password", null, false);
        }
        
        return new AuthResponse("Login successful", user, true);
    }

    public AuthResponse register(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        
        if (existingUser.isPresent()) {
            return new AuthResponse("Email already exists", null, false);
        }
        
        User savedUser = userRepository.save(user);
        return new AuthResponse("Registration successful", savedUser, true);
    }
}