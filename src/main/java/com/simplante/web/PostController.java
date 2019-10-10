package com.simplante.web;

import com.simplante.dto.CategoryDto;
import com.simplante.dto.PostDto;
import com.simplante.dto.mapper.ObjectMapperUtils;
import com.simplante.model.Post;
import com.simplante.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/posts")
@Slf4j
public class PostController {

    private final PostService postService;


    /**
     * @param postService
     */
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * @return
     */
    @GetMapping
    public ResponseEntity<?> listAllPostsOrderByDateDesc() {
        log.debug("get list All Posts Order By Date Desc");
        return new ResponseEntity<>(ObjectMapperUtils.mapAll(postService.listPostOrderByCreateDateDesc(), PostDto.class),
                HttpStatus.OK);
    }


    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            if (postService.findById(id) == null)
                return new ResponseEntity<>(new Exception("Post not exist"), HttpStatus.NOT_FOUND);

            log.debug("Get post by ID");
            return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Find by id : ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}/categories")
    public ResponseEntity<?> getCategoryByPosts(@PathVariable Long id) {
        try {
            Post post = postService.findById(id);
            if (post == null)
                throw new Exception("POST_NOT_FOUND");
            return ResponseEntity.ok(ObjectMapperUtils.map(postService.getCategoryByPost(post), CategoryDto.class));
            /*return new ResponseEntity<>(result, HttpStatus.OK);*/
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * @param postDto
     * @return
     */
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody @Valid PostDto postDto) {

        try {
            if (postDto.getId() != null)
                return new ResponseEntity<>(new Exception("Please remove the Id"), HttpStatus.UNAUTHORIZED);

            if (postService.findByTitle(postDto.getTitle()) != null)
                return new ResponseEntity<>(new Exception("Title already exist"), HttpStatus.CONFLICT);

            Post result = postService.savePost(ObjectMapperUtils.map(postDto, Post.class));
            return new ResponseEntity<>(ObjectMapperUtils.map(result, PostDto.class), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * @param id
     * @param postDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody @Valid PostDto postDto) {

        try {

            if (postService.findById(id) == null)
                return new ResponseEntity<>("This post not exist", HttpStatus.FORBIDDEN);
            postDto.setId(id);
            Post result = postService.updatePost(ObjectMapperUtils.map(postDto, Post.class));
            return new ResponseEntity<>(ObjectMapperUtils.map(result, PostDto.class), HttpStatus.CREATED);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }


    /**
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        try {
            if (postService.findById(id) == null)
                throw new Exception("This post not exist");
            postService.deletePost(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("delete post : {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    /**
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Object> deleteAllProjects() {
        try {
            Long count = postService.countPost();
            postService.deleteAllPost();
            log.debug("delete " + count + " post");
            return new ResponseEntity<>("deleted " + count + " posts", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}

