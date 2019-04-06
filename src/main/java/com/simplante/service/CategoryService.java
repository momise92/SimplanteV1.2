package com.simplante.service;

import com.simplante.model.Category;
import com.simplante.model.Post;

import java.util.List;

/**
 * @author Moïse Coulanges
 * @version 0.1
 */
public interface CategoryService {

    List<Category> ListCategories();

    Category createCategory(Category category) throws Exception;

    Category updateCategory(Category category) throws Exception;

    Category findById(Long id);

    Category findByName(String name);

    List<Post> getPostsByCategory(Long categoryId) throws Exception;

    void deleteCategory(Long id);

    void deleteAllCategory();

    Long countCategory();

}
