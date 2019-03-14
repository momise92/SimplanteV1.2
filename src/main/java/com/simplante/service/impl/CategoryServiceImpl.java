package com.simplante.service.impl;

import com.simplante.dao.CategoryRepository;
import com.simplante.dao.PostRepository;
import com.simplante.model.Category;
import com.simplante.model.Post;
import com.simplante.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {


    private CategoryRepository categoryRepository;
    private PostRepository postRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, PostRepository postRepository) {
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;

    }

    @Override
    public List<Category> ListCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    @Override
    public Category createCategory(Category category) throws Exception {
        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            log.error("");
            throw new Exception("Cannot save the post ",e);
        }
    }

    @Override
    public Category updateCategory(Category category) throws Exception {
        Optional<Category> result = categoryRepository.findById(category.getId());
        if(!result.isPresent())
            throw new Exception("POST_ID "+category.getId()+ "NOT_FOUND");
        category.setName(category.getName());
        return categoryRepository.save(category);
    }


    @Override
    public Category findById(Long id) {
        if(categoryRepository.findById(id).isPresent())
            return categoryRepository.findById(id).get();
        return null;
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }


    @Override
    public List<Post> getPostsByCategory(Long categoryId) throws Exception {
        if (!categoryRepository.findById(categoryId).isPresent()) {
            log.error("Cannot find Category " + categoryId);
            throw new Exception("Category not found" + categoryId);
        }
        return postRepository.findByCategoryId(categoryId);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteAllCategory() {
        categoryRepository.deleteAll();
    }

    @Override
    public Long countCategory() {
        return categoryRepository.count();
    }
}
