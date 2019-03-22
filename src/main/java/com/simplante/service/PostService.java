package com.simplante.service;


import com.simplante.model.Category;
import com.simplante.model.Post;

import java.util.List;

public interface PostService {

    List<Post> listPost();

    List<Post> listPostOrderByCreateDateDesc();

    Post savePost(Post post) throws Exception;

    Post updatePost(Post post) throws Exception;

    Post findById(Long id) throws Exception;

    Post findByTitle(String title);

    void deletePost(Long id);

    void deleteAllPost();

    Long countPost();

    Category getCategoryByPost(Post post) throws Exception;
}
