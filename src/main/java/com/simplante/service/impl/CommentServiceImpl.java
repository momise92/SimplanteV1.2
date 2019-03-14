package com.simplante.service.impl;


import com.simplante.dao.CommentRepository;
import com.simplante.dao.PostRepository;
import com.simplante.model.Comment;
import com.simplante.model.Post;
import com.simplante.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @Override
    public List<Comment> ListComments() {
        return commentRepository.findAllByOrderByCreateDateDesc();
    }


    @Override
    @Transactional
    public Comment createComment(Comment comment) throws Exception {
        Comment result;

        try {
            /*if (postRepository.findById(comment.getPostId()).isPresent())
                throw new Exception("POST_NOT_FOUND " + comment.getPostId());*/

            Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
            List<String> errors = new ArrayList<>();
            for (ConstraintViolation<Comment> violation : violations) {
                errors.add(violation.getMessage());
                log.error("Validation error : " + violation.getMessage());
            }
            if (!errors.isEmpty())
                throw new Exception(errors.toString());

            result = commentRepository.save(comment);
            Post post = postRepository.findById(comment.getPostId()).get();
            post.addComment(comment);
            postRepository.save(post);
        }

        catch (Exception e) {
            for (Throwable t = e.getCause(); t != null; t = t.getCause()) {
                log.error("Exception:" + t.getMessage());
            }
            throw e;
        }

        return result;
    }

    @Override
    public Comment updateComment(Comment comment) throws Exception {
        Comment result;
        try {
            Optional<Comment> comment1 = commentRepository.findById(comment.getId());
            if(!comment1.isPresent())
                throw new Exception("COMMENT "+comment.getId()+" NOT_FOUND");
            comment.setBody(comment.getBody());
            comment.setPostId(comment.getPostId());
            comment.setUpdateDate(new Date());
            result=commentRepository.save(comment);
        }
        catch (Exception e) {
            throw new Exception("Cannot update comment ",e);
        }
        return result;
        }

    @Override
    public Comment findById(Long id) throws Exception {
        return commentRepository.findById(id)
                .orElseThrow(()->new Exception("COMMENT_ID " +id+" NOT_FOUND"));
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);

    }

    @Override
    public void deleteAllComments() {
        commentRepository.deleteAll();

    }

    @Override
    public Long countComment() {
        return commentRepository.count();
    }
}
