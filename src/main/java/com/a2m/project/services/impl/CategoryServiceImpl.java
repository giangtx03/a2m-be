package com.a2m.project.services.impl;

import com.a2m.project.daos.CategoryDAO;
import com.a2m.project.domains.Category;
import com.a2m.project.mapper.CategoryMapper;
import com.a2m.project.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;
    @Override
    public List<Category> getAll(String keyword) {
        List<Map<String, Object>> list = categoryDAO.selectAllCategory(keyword);
        return list.stream().map(CategoryMapper::toCategory)
                .toList();
    }

    @Override
    public Category getCategoryById(Long id) throws NotFoundException {
        Map<String, Object> source = categoryDAO.selectCategoryById(id);
        if(source == null){
            throw new NotFoundException(String.format("Không tìm thấy category với id = %s", id));
        }
        return CategoryMapper.toCategory(source);
    }

    @Override
    public Category createCategory(Category category) {
        category.setCreateAt(LocalDateTime.now());
        category.setUpdateAt(LocalDateTime.now());
        categoryDAO.insert(category);
        return category;
    }

    @Override
    public Category updateCategory(Category category) {
        category.setUpdateAt(LocalDateTime.now());
        categoryDAO.update(category);
        return category;
    }

    @Override
    public void delete(Long id) {
        categoryDAO.deleteCategoryById(id);
    }
}
