package com.a2m.project.services.impl;

import com.a2m.project.daos.CategoryDAO;
import com.a2m.project.daos.ProductDAO;
import com.a2m.project.domains.Category;
import com.a2m.project.domains.Product;
import com.a2m.project.dtos.requests.ProductRequest;
import com.a2m.project.dtos.responses.ListResponse;
import com.a2m.project.mapper.CategoryMapper;
import com.a2m.project.mapper.ProductMapper;
import com.a2m.project.services.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;
    private final CategoryDAO categoryDAO;

    @Override
    public ListResponse getAll(String keyword, Long categoryId, int pageNumber, int limit) {
        PageHelper.startPage(pageNumber,limit);
        List<Map<String, Object>> list = productDAO.selectAllProduct(keyword, categoryId);

        List<Product> products =  list.stream().map(item -> {
            Category category = CategoryMapper.toCategory(categoryDAO.selectCategoryById(Long.parseLong(item.get("categoryId").toString())));
            return ProductMapper.toProduct(item, category);
        }).toList();
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);

        return ListResponse.builder()
                .totalItems(pageInfo.getTotal())
                .items(products)
                .totalPages(pageInfo.getPages())
                .build();
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        List<Map<String, Object>> list = productDAO.selectProductsByCategoryId(categoryId);

        return list.stream().map(item -> {
            Category category = CategoryMapper.toCategory(categoryDAO.selectCategoryById(categoryId));
            return ProductMapper.toProduct(item, category);
        }).toList();
    }

    @Override
    public Product getProductById(Long id) throws NotFoundException {
        Map<String, Object> item = productDAO.selectProductById(id);
        if(item == null){
            throw new NotFoundException(String.format("Không tìm thấy product với id = %s", id));
        }
        Category category = CategoryMapper.toCategory(categoryDAO.selectCategoryById(Long.parseLong(item.get("categoryId").toString())));
        return ProductMapper.toProduct(item, category);
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        Category category = CategoryMapper.toCategory(categoryDAO.selectCategoryById(productRequest.getCategory()));

        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .category(category)
                .build();
        product.setCreateAt(LocalDateTime.now());
        product.setUpdateAt(LocalDateTime.now());
        product.setCode(String.valueOf(System.currentTimeMillis()));
        productDAO.insert(product);
        return product;
    }

    @Override
    public Product updateProduct(ProductRequest productRequest) throws NotFoundException {
        Category category = CategoryMapper.toCategory(categoryDAO.selectCategoryById(productRequest.getCategory()));
        Product product = getProductById(productRequest.getId());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setCategory(category);
        product.setUpdateAt(LocalDateTime.now());
        productDAO.update(product);
        return product;
    }

    @Override
    public void delete(Long id) {
        productDAO.deleteProductById(id);
    }
}
