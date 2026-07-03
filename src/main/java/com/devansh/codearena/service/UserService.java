package com.devansh.codearena.service;

import com.devansh.codearena.dto.LoginRequest;
import com.devansh.codearena.dto.LoginResponse;
import com.devansh.codearena.dto.RegisterRequest;
import com.devansh.codearena.entity.User;

public interface UserService {
    User register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
