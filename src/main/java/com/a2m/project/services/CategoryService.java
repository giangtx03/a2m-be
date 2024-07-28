package com.a2m.project.services;

import com.a2m.project.domains.Category;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface CategoryService {
    List<Category> getAll(String keyword);
    Category getCategoryById(Long id) throws NotFoundException;
    Category createCategory(Category category);
    Category updateCategory(Category category);
    void delete(Long id);
}
