package com.simplante.service.impl;

import com.simplante.dao.CommentRepository;
import com.simplante.dao.PostRepository;
import com.simplante.dao.RoleAppRepository;
import com.simplante.dao.UserAppRepository;
import com.simplante.model.Comment;
import com.simplante.model.Post;
import com.simplante.model.RoleApp;
import com.simplante.model.UserApp;
import com.simplante.service.UserAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserAppServiceImpl implements UserAppService {

    private UserAppRepository userAppRepository;
    private RoleAppRepository roleAppRepository;
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private PasswordEncoder passwordEncoder;

    public UserAppServiceImpl(UserAppRepository userAppRepository, RoleAppRepository roleAppRepository, CommentRepository commentRepository, PostRepository postRepository, PasswordEncoder passwordEncoder) {
        this.userAppRepository = userAppRepository;
        this.roleAppRepository = roleAppRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserApp> ListUsers() {
        return userAppRepository.findAll();
    }

    @Override
    public UserApp findById(Long id) throws Exception {
        return userAppRepository.findById(id)
                .orElseThrow(()->new Exception("USER_ID " +id+" NOT_FOUND"));
    }

    @Override
    public UserApp findUserByUsername(String username) {
        return userAppRepository.findByUsername(username);

    }

    @Override
    public UserApp currentUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userAppRepository.findByUsername(username);
    }


    @Transactional
    @Override
    public UserApp createUser(UserApp userApp) throws Exception {
        if (userAppRepository.existsByUsername(userApp.getUsername().toLowerCase()))
            throw new Exception("user already exist");
        if (userAppRepository.existsByEmail(userApp.getEmail()))
            throw new Exception("Email already exist");
        if (userApp.getRegisterDate() == null)
            userApp.setRegisterDate(LocalDateTime.now());
        userApp.setUsername(userApp.getUsername().toLowerCase());
        userApp.setPassword(passwordEncoder.encode(userApp.getPassword()));
        userApp.getRoles().add(roleAppRepository.findByRole("USER"));

        return userAppRepository.save(userApp);
    }

    @Override
    public UserApp updateUser(UserApp userApp) throws Exception {
        Optional<UserApp>result = userAppRepository.findById(userApp.getId());
        if(!result.isPresent()) {
            log.error("Cannot update user" + userApp + " it's not present");
            throw new Exception("USER_ID "+ userApp.getId()+" NOT_FOUND");
        }
        userApp.setRegisterDate(result.get().getRegisterDate());
        userApp.setPassword(result.get().getPassword());
        return userAppRepository.save(userApp);
    }

    @Override
    public void deleteUser(Long id) {
        userAppRepository.deleteById(id);

    }

    @Override
    @Transactional
    public void addRoleToUser(String username, String role) {
        UserApp userApp = userAppRepository.findByUsername(username);
        RoleApp roleApp = roleAppRepository.findByRole(role);
        userApp.getRoles().add(roleApp);
    }

    @Override
    public List<Comment> getCommentByUser(UserApp userApp) throws Exception {
        Optional<UserApp> userId = userAppRepository.findById(userApp.getId());
        if (!userId.isPresent()) {
            log.error("Cannot find user " + userApp.getId());
            throw new Exception("Category not found" + userId);
        }
        return commentRepository.findByUser(userApp);
    }

    @Override
    public List<Post> getPostByCurrentUser() throws Exception {
       String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserApp userApp = userAppRepository.findByUsername(username);
        if(userApp.getPosts().isEmpty()){
            log.error("Cannot find post for this user " + userApp.getUsername());
        throw new Exception("Post not found for this user " + userApp.getUsername());
        }
        return postRepository.findByUser(userApp);
    }
}



