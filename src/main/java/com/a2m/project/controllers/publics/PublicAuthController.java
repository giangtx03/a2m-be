package com.a2m.project.controllers.publics;

import com.a2m.project.dtos.requests.LoginRequest;
import com.a2m.project.dtos.requests.RegisterRequest;
import com.a2m.project.dtos.responses.LoginResponse;
import com.a2m.project.dtos.responses.RegisterResponse;
import com.a2m.project.dtos.responses.ResponseData;
import com.a2m.project.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("public/${api.prefix}/users")
@RequiredArgsConstructor
public class PublicAuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest registerRequest
    ){
        try {
            RegisterResponse registerResponse =  userService.register(registerRequest);
            ResponseData response = ResponseData.builder()
                    .status(HttpStatus.ACCEPTED)
                    .message("Đăng ký thành công")
                    .data(registerResponse)
                    .build();
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            ResponseData response = ResponseData.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest loginRequest
    ){
        try {
            LoginResponse loginResponse =  userService.login(loginRequest);
            ResponseData response = ResponseData.builder()
                    .status(HttpStatus.ACCEPTED)
                    .message("Đăng ký thành công")
                    .data(loginResponse)
                    .build();
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            ResponseData response = ResponseData.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
