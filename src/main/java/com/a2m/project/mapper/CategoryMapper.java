package com.a2m.project.mapper;

import com.a2m.project.domains.Category;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class CategoryMapper {
    public static Category toCategory(Map<String, Object> source){
        return Category.builder()
                .id(Long.parseLong(source.get("id").toString()))
                .name(source.get("name").toString())
                .description(source.get("description") != null ? source.get("description").toString() : "")
                .totalProduct(source.get("totalProduct") != null ? Long.parseLong(source.get("totalProduct").toString()) : null)
                .createAt(source.get("createAt") != null ? LocalDateTime.parse(source.get("createAt").toString().replace(".0",""), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null)
                .updateAt(source.get("updateAt") != null ? LocalDateTime.parse(source.get("updateAt").toString().replace(".0",""), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null)
                .build();
    }
}
