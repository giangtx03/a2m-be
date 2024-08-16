package com.a2m.project.services;

import com.a2m.project.domains.Product;
import com.a2m.project.dtos.requests.ProductRequest;
import com.a2m.project.dtos.responses.ListResponse;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface ProductService {
    ListResponse getAll(String keyword, Long categoryId, int pageNumber, int limit);
    List<Product> getProductsByCategoryId(Long categoryId);
    Product getProductById(Long id) throws NotFoundException;
    Product createProduct(ProductRequest productRequest);
    Product updateProduct(ProductRequest productRequest) throws NotFoundException;
    void delete(Long id);
}
