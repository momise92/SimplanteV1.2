package com.simplante.web;


import com.simplante.model.Comment;
import com.simplante.model.Post;
import com.simplante.service.CommentService;
import com.simplante.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }



    @GetMapping("/comments")
    public ResponseEntity<?> listAllComments(){
        log.debug("get list Comment");
        return new ResponseEntity<>(commentService.ListComments(),HttpStatus.OK);
    }


    /**
     * @return
     */
    @GetMapping("/comments/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        try {
            if (commentService.findById(id) == null)
                return new ResponseEntity<Object>(new Exception("Category not exist"),HttpStatus.NOT_FOUND);

            log.debug("Get comment by ID");
            return new ResponseEntity<>(commentService.findById(id),HttpStatus.OK);
        } catch (Exception e) {
            log.error("Find by id : ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Object> createComment(@PathVariable Long postId, @RequestBody @Valid Comment comment) {
        try {
            if (comment.getId() != null)
                return new ResponseEntity<>(new Exception("Please remove the Id"),HttpStatus.UNAUTHORIZED);
            Post post = postService.findById(postId);
            if (post == null)
                throw new Exception("POST_NOT_FOUND");
            comment.setPostId(postId);
            return new ResponseEntity<>(commentService.createComment(comment), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Object> updateComment(@PathVariable Long id, @RequestBody @Valid Comment comment) {
        try {
            Comment comment1 = commentService.findById(id);
            if (comment1 == null)
                return new ResponseEntity<>(new Exception("COMMENT NOT FOUND"),HttpStatus.NOT_FOUND);

            comment.setId(id);
            comment.setPostId(comment1.getPostId());
            return new ResponseEntity<>(commentService.updateComment(comment), HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @return
     */
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?>deleteComment(@PathVariable Long id){
        try {
            if (commentService.findById(id) == null)
                throw new Exception("Comment not exist");
            commentService.deleteComment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("delete category :", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/comments")
    public ResponseEntity<Object> deleteAllComments() {
        try {
            Long count = commentService.countComment();
            commentService.deleteAllComments();
            log.debug("delete " + count + " comments");
            return new ResponseEntity<>("deleted " + count + " comments", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
