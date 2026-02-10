package com.tcs.springboot.employees.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.springboot.employees.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {

        // demo validation — replace with DB validation
        if ("admin".equals(username) && "admin123".equals(password)) {
            return jwtUtil.generateToken(username);
        }

        throw new RuntimeException("Invalid credentials");
    }
}
