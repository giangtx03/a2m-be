package com.a2m.project.services;

import com.a2m.project.domains.Category;
import com.a2m.project.dtos.responses.ListResponse;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface CategoryService {
    ListResponse getAll(String keyword, int pageNumber, int limit);
    Category getCategoryById(Long id) throws NotFoundException;
    Category createCategory(Category category);
    Category updateCategory(Category category);
    void delete(Long id);
}
