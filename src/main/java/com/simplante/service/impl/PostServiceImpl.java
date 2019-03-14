package com.simplante.service.impl;

import com.simplante.dao.CategoryRepository;
import com.simplante.dao.PostRepository;
import com.simplante.model.Category;
import com.simplante.model.Post;
import com.simplante.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Post> listPost() {
        log.debug("Get all posts ");
        return postRepository.findAll();
    }

    @Override
    public List<Post> listPostOrderByCreateDateDesc() {
        return postRepository.findAllByOrderByCreateDateDesc();
    }


    @Transactional
    @Override
    public Post savePost(Post post) throws Exception {
        try {
            post.setCreateDate(LocalDateTime.now());
            post.setLastModified(LocalDateTime.now());

            return postRepository.save(post);

        } catch (Exception e) {
            log.error("Cannot save post:" + post);
            throw new Exception("Cannot save the post ",e);
        }
    }


    @Override
    public Post updatePost(Post post) throws Exception {
        Optional<Post>result = postRepository.findById(post.getId());
        if(!result.isPresent()) {
            log.error("Cannot update post" + post + " it's not present");
            throw new Exception("POST_ID "+ post.getId()+" NOT_FOUND");
        }
        post.setLastModified(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public Post findById(Long id) throws Exception {
        return postRepository.findById(id)
                .orElseThrow(()->new Exception("PROJECT_ID "+id+" NOTFOUND"));
    }

    @Override
    public Post findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAllPost() {
         postRepository.deleteAll();
    }

    @Override
    public Long countPost() {
        return postRepository.count();
    }

    @Override
    public Category getCategoryByPost(Post post) throws Exception {
        Optional<Post> postId = postRepository.findById(post.getId());
        if (!postId.isPresent()) {
            log.error("Cannot find post " + post.getId());
            throw new Exception("Category not found" + postId);
        }
        return categoryRepository.findByPosts(post);
    }

}
