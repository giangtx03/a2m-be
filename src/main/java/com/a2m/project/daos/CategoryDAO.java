package com.a2m.project.daos;

import com.a2m.project.domains.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryDAO {

    void insert(Category category);
    void update(Category category);
    void deleteCategoryById(Long id);
    List<Map<String, Object>> selectAllCategory(
            @Param("keyword") String keyword
    );
    Map<String, Object> selectCategoryById(Long id);
}
