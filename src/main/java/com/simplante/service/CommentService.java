package com.simplante.service;

import com.simplante.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> ListComments();

    Comment createComment(Comment comment) throws Exception;

    Comment updateComment(Comment comment) throws Exception;

    Comment findById(Long id) throws Exception;

    void deleteComment(Long id);

    void deleteAllComments();

    Long countComment();
}
