package com.a2m.project.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private Long id;

    private String name;
    private Double price;

    private String description;

    private Long category;
}
