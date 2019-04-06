package com.simplante.service;

import com.simplante.model.Comment;
import com.simplante.model.Post;
import com.simplante.model.UserApp;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * @author Moïse Coulanges
 * @version 0.1
 */
public interface UserAppService {

    List<UserApp> ListUsers();

    UserApp findById(Long id) throws Exception;

    UserApp findUserByUsername(String username);

    UserApp createUser(UserApp userApp) throws Exception;

    UserApp updateUser(UserApp userApp) throws Exception;

    void deleteUser(Long id);

    void addRoleToUser(String username, String role);

    List<Comment> getCommentByUser(UserApp userApp) throws Exception;

    List<Post> getPostByCurrentUser() throws Exception;

}
