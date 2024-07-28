package com.a2m.project.services;

import com.a2m.project.domains.Product;
import com.a2m.project.dtos.requests.ProductRequest;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface ProductService {
    List<Product> getAll(String keyword, Long categoryId);
    List<Product> getProductsByCategoryId(Long categoryId);
    Product getProductById(Long id) throws NotFoundException;
    Product createProduct(ProductRequest productRequest);
    Product updateProduct(ProductRequest productRequest) throws NotFoundException;
    void delete(Long id);
}
