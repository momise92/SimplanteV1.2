package com.simplante.service;

import com.simplante.model.Comment;
import com.simplante.model.UserApp;

import java.util.List;

public interface UserAppService {
    List<UserApp> ListUsers();
    UserApp findById(Long id) throws Exception;
    UserApp findUserByUsername(String username) throws Exception;
    UserApp createUser(UserApp userApp) throws Exception;
    UserApp updateUser(UserApp userApp) throws Exception;
    void deleteUser(Long id);
    void addRoleToUser(String username,String role);
    List<Comment> getCommentByUser(UserApp userApp) throws Exception;


}
