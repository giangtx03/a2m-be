package com.a2m.project.controllers.publics;

import com.a2m.project.domains.Category;
import com.a2m.project.dtos.responses.ResponseData;
import com.a2m.project.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("public/${api.prefix}/categories")
public class PublicCategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ResponseData> getAllCategories(
            @RequestParam("keyword") @Nullable String keyword
    ){

        List<Category> categories = categoryService.getAll(keyword);
        ResponseData response = ResponseData.builder()
                .status(HttpStatus.ACCEPTED)
                .message("Danh sách categories")
                .data(categories)
                .build();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getCategoryId(
            @PathVariable("id") Long id
    ){
        try {
            Category category = categoryService.getCategoryById(id);
            ResponseData response = ResponseData.builder()
                    .status(HttpStatus.ACCEPTED)
                    .message("Danh sách categories")
                    .data(category)
                    .build();

            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            ResponseData response = ResponseData.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();

            return ResponseEntity.ok().body(response);
        }
    }

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
