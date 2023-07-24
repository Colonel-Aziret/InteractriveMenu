package com.example.interactrivemenu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginTestController
{
    @GetMapping("/admin-login")
    public String adminValidation()
    {
        return "Admin user, Successfully logged in!";
    }
}
