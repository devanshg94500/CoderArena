package com.devansh.codearena.controller;

import com.devansh.codearena.dto.LoginRequest;
import com.devansh.codearena.dto.LoginResponse;
import com.devansh.codearena.dto.RegisterRequest;
import com.devansh.codearena.entity.User;
import com.devansh.codearena.security.JwtService;
import com.devansh.codearena.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/token")
    public String generateToken() {
        return jwtService.generateToken("devansh@gmail.com");
    }

    @GetMapping("/profile")
    public String profile() {
        return "Welcome to CodeArena!";
    }
}