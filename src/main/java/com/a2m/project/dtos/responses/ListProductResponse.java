package com.a2m.project.dtos.responses;

import com.a2m.project.domains.Product;

import java.util.List;

public class ListProductResponse {
    private List<Product> products;
    private Long totalProduct;
    private Long totalPages;
}
