package com.a2m.project.controllers;

import com.a2m.project.domains.Product;
import com.a2m.project.dtos.requests.ProductRequest;
import com.a2m.project.dtos.responses.ResponseData;
import com.a2m.project.services.ProductService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/products")
public class ProductController {

    private final ProductService productService;


    @PreAuthorize("ROLE_ADMIN")
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(
            @PathVariable("id") Long id
    ){
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok().body(product);
        }catch (Exception e){
            ResponseData response = ResponseData.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PreAuthorize("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody ProductRequest productDto
    ){
        try {
            Product product = productService.createProduct(productDto);
            ResponseData response = ResponseData.builder()
                    .status(HttpStatus.CREATED)
                    .message("Tạo sản phẩm thành công")
                    .data(product)
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
    public ResponseEntity<?> update(
            @RequestBody ProductRequest productRequest
    ){
        try {
            Product productUpdate = productService.updateProduct(productRequest);
            ResponseData response = ResponseData.builder()
                    .status(HttpStatus.CREATED)
                    .message("Tạo sản phẩm thành công")
                    .data(productUpdate)
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
    public ResponseEntity<?> deleteProductById(
            @PathVariable("id") Long id
    ){
        try {
            productService.delete(id);
            ResponseData response = ResponseData.builder()
                    .status(HttpStatus.ACCEPTED)
                    .message("Xóa sản phẩm thành công")
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
