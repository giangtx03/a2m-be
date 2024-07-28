package com.a2m.project.controllers;

import com.a2m.project.domains.Category;
import com.a2m.project.domains.Product;
import com.a2m.project.dtos.responses.ResponseData;
import com.a2m.project.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PreAuthorize("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<ResponseData> create(
            @RequestBody Category category
    ){
        try {
            Category categoryResponse = categoryService.createCategory(category);
            ResponseData response = ResponseData.builder()
                    .status(HttpStatus.CREATED)
                    .message("Tạo category thành công")
                    .data(categoryResponse)
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

    @PreAuthorize("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<ResponseData> update(
            @RequestBody Category category
    ){
        try {
            Category categoryResponse = categoryService.updateCategory(category);
            ResponseData response = ResponseData.builder()
                    .status(HttpStatus.ACCEPTED)
                    .message("Cập nhật category thành công")
                    .data(categoryResponse)
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

    @PreAuthorize("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> delete(
            @PathVariable("id") Long id
    ){
        try {
            categoryService.delete(id);
            ResponseData response = ResponseData.builder()
                    .status(HttpStatus.ACCEPTED)
                    .message("Xóa category thành công")
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
