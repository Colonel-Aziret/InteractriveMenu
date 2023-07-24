package com.example.interactrivemenu.controller;

import com.example.interactrivemenu.enums.Role;
import com.example.interactrivemenu.model.User;
import com.example.interactrivemenu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginTestController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/admin-login")
    public String adminValidation() {
        return "Admin user, Successfully logged in!";
    }

    @PostMapping("/reg")
    public User save(@RequestBody User user) {
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }
}
