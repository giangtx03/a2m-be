package com.a2m.project.domains;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity{

    private Long id;

    private String name;

    private String code;

    private Double price;

    private String description;

    private Category category;

}
