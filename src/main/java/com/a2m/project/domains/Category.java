package com.a2m.project.domains;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity{
    private Long id;

    private String name;

    private String description;

    private Long totalProduct;
}
