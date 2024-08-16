package com.a2m.project.controllers.publics;

import com.a2m.project.domains.Product;
import com.a2m.project.dtos.requests.ProductRequest;
import com.a2m.project.dtos.responses.ListResponse;
import com.a2m.project.dtos.responses.ResponseData;
import com.a2m.project.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("public/${api.prefix}/products")
@RequiredArgsConstructor
public class PublicProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam("keyword") @Nullable String keyword,
            @RequestParam("category_id") @Nullable Long categoryId,
            @RequestParam(name = "page_number",defaultValue = "1") int pageNumber,
            @RequestParam(name = "limit",defaultValue = "10") int limit
    ){
        ListResponse listResponse = productService.getAll(keyword,categoryId, pageNumber, limit);

        ResponseData response = ResponseData.builder()
                .status(HttpStatus.ACCEPTED)
                .message("Danh sách sản phẩm")
                .data(listResponse)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(
            @PathVariable("id") Long id
    ){
        try {
            Product product = productService.getProductById(id);
            ResponseData response = ResponseData.builder()
                    .status(HttpStatus.ACCEPTED)
                    .message("Sản phẩm với id = " + id)
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


    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody ProductRequest productRequest
    ){
        try {
            Product product = productService.createProduct(productRequest);
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

    @PutMapping
    public ResponseEntity<?> update(
            @RequestBody ProductRequest productRequest
    ){
        try {
            Product productUpdate = productService.updateProduct(productRequest);
            ResponseData response = ResponseData.builder()
                    .status(HttpStatus.ACCEPTED)
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
