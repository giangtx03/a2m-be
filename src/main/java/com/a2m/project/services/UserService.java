package com.a2m.project.services;

import com.a2m.project.dtos.requests.LoginRequest;
import com.a2m.project.dtos.requests.RegisterRequest;
import com.a2m.project.dtos.responses.LoginResponse;
import com.a2m.project.dtos.responses.RegisterResponse;

public interface UserService {

    LoginResponse login(LoginRequest loginRequest) throws Exception;
    RegisterResponse register(RegisterRequest registerRequest) throws Exception;

}
