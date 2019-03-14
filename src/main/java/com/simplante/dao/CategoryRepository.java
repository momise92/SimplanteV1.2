package com.simplante.dao;

import com.simplante.model.Category;
import com.simplante.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);
    Category findByPosts(Post post);
}
