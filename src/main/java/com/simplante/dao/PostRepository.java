package com.simplante.dao;

import com.simplante.model.Category;
import com.simplante.model.Post;
import com.simplante.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Moïse Coulanges
 * @version 0.1
 */
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreateDateDesc();

    Post findByTitle(String title);

    List<Post> findByCategoryId(Long categoryId);

    List<Post> findByCategory(Category category);

    List<Post>findByUser(UserApp userApp);
}
