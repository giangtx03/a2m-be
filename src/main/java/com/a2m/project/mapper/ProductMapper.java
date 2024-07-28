package com.a2m.project.mapper;

import com.a2m.project.domains.Category;
import com.a2m.project.domains.Product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ProductMapper {
    public static Product toProduct(Map<String, Object> source, Category category){
        return Product.builder()
                .id(Long.parseLong(source.get("id").toString()))
                .name(source.get("name").toString())
                .price(Double.parseDouble(source.get("price").toString()))
                .description(source.get("description")!= null ? source.get("description").toString() : "")
                .code(source.get("code") != null ? source.get("code").toString() : "")
                .createAt(source.get("createAt") != null ? LocalDateTime.parse(source.get("createAt").toString().replace(".0",""), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null)
                .updateAt(source.get("updateAt") != null ? LocalDateTime.parse(source.get("updateAt").toString().replace(".0",""), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null)
                .category(category)
                .build();
    }
}
