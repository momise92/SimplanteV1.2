package com.simplante.web;

import com.simplante.dto.PostDto;
import com.simplante.dto.mapper.CategoryMapper;
import com.simplante.dto.mapper.PostMapper;
import com.simplante.model.Post;
import com.simplante.service.CategoryService;
import com.simplante.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 *
 */
@RestController
@RequestMapping("/api/posts")
@Slf4j
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public PostController(PostService postService, PostMapper postMapper,
                          CategoryService categoryService, CategoryMapper categoryMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    /**
     * @return
     */

    /*@GetMapping
    public ResponseEntity<?>listAllPosts(){
        log.debug("get list All Posts");
        return new ResponseEntity<>(postMapper.listPostsToListPostsDto(postService.listPost()),
                HttpStatus.OK);
    }*/

    /**
     * @return
     */
    @GetMapping
    public ResponseEntity<?>listAllPostsOrderByDateDesc(){
        log.debug("get list All Posts Order By Date Desc");
        return new ResponseEntity<>(postMapper.listPostsToListPostsDto(postService.listPostOrderByCreateDateDesc()),
                HttpStatus.OK);
    }


    /**
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        try {
            if (postService.findById(id) == null)
                return new ResponseEntity<Object>(new Exception("Post not exist"),HttpStatus.NOT_FOUND);

            log.debug("Get post by ID");
            return new ResponseEntity<>(postService.findById(id),HttpStatus.OK);
        } catch (Exception e) {
            log.error("Find by id : ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/categories")
    public ResponseEntity<?> getCategoryByPosts(@PathVariable Long id) {
        try {
            Post post = postService.findById(id);
            if (post == null)
                throw new Exception("POST_NOT_FOUND");
            return ResponseEntity.ok(categoryMapper.categoryToDto(postService.getCategoryByPost(post)));
            /*return new ResponseEntity<>(result, HttpStatus.OK);*/
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * @return
     */
    @PostMapping
    public ResponseEntity<?>createPost(@RequestBody @Valid PostDto postDto) {

        try {
        if (postDto.getId() != null)
            return new ResponseEntity<Object>(new Exception("Please remove the Id"),HttpStatus.UNAUTHORIZED);

        if (postService.findByTitle(postDto.getTitle()) != null)
            return new ResponseEntity<Object>(new Exception("Title already exist"),HttpStatus.CONFLICT);

            Post result = postService.savePost(postMapper.postDtoToPost(postDto));
            return new ResponseEntity<>(postMapper.postToPostDto(result), HttpStatus.CREATED);
        }
        catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?>updatePost(@PathVariable Long id, @RequestBody @Valid  PostDto postDto) {

        try {

            if (postService.findById(id) == null)
                return new ResponseEntity<>("This post not exist", HttpStatus.FORBIDDEN);
            postDto.setId(id);
            Post result = postService.updatePost(postMapper.postDtoToPost(postDto));
            return new ResponseEntity<>(postMapper.postToPostDto(result), HttpStatus.CREATED);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }


    /**
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deletePost(@PathVariable Long id){
        try {
            if (postService.findById(id) == null)
                throw new Exception("This post not exist");
            postService.deletePost(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("delete post :", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

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

