package com.a2m.project.dtos.responses;

import com.a2m.project.domains.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListResponse {
    @JsonProperty("items")
    private Object items;

    @JsonProperty("total_items")
    private Long totalItems;

    @JsonProperty("total_pages")
    private int totalPages;
}
