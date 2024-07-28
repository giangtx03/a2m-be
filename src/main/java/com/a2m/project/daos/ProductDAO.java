package com.a2m.project.daos;

import com.a2m.project.domains.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductDAO {
    void insert(Product product);
    void update(Product product);
    void deleteProductById(Long id);
    Map<String, Object> selectProductById(Long id);
    List<Map<String, Object>> selectAllProduct(@Param("keyword") String keyword, @Param("categoryId") Long categoryId);
    List<Map<String, Object>> selectProductsByCategoryId(Long categoryId);
}
