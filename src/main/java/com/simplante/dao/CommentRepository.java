package com.simplante.dao;

import com.simplante.model.Comment;
import com.simplante.model.Post;
import com.simplante.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment>findByPost(Post post);
    List<Comment> findAllByOrderByCreateDateDesc();
    List<Comment>findByUser(UserApp userApp);
}
