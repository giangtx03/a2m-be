package com.a2m.project.controllers;

import com.a2m.project.domains.Category;
import com.a2m.project.dtos.responses.ResponseData;
import com.a2m.project.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class AuthController {

    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getUserDetails(
            @PathVariable("id") Long id
    ){
        try {
            ResponseData response = ResponseData.builder()
                    .status(HttpStatus.CREATED)
                    .message("Lấy thông tin người dùng thành công")
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
