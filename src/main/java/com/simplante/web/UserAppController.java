package com.simplante.web;


import com.simplante.dto.PostDto;
import com.simplante.dto.UserDto;
import com.simplante.dto.UserRegistrationDto;
import com.simplante.dto.mapper.ObjectMapperUtils;
import com.simplante.dto.mapper.PostMapper;
import com.simplante.dto.mapper.RegistrationMapper;
import com.simplante.dto.mapper.UserMapper;
import com.simplante.model.Post;
import com.simplante.model.UserApp;
import com.simplante.service.UserAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Moïse Coulanges
 * @version 0.1
 */
@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserAppController {

    private UserAppService userAppService;


    public UserAppController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }


    /**
     * @return response entity status 200 and list of users on body
     */
    @GetMapping
    public ResponseEntity<?> listUsers() {
        log.debug("get All users");
        return new ResponseEntity<>(ObjectMapperUtils.mapAll(userAppService.ListUsers(), UserDto.class), HttpStatus.OK);
    }


    /**
     * @param id the id of user to show
     * @return response entity status 404 if user is not found
     * or status 200 if the user is found and the user on body
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            if (userAppService.findById(id) == null)
                return new ResponseEntity<Object>(new Exception("User not exist"), HttpStatus.NOT_FOUND);

            log.debug("Get user by ID");
            return new ResponseEntity<>(userAppService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Find user by id : {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * @return response entity status 200 and the current users on body
     */
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser() {
        log.debug("get Current users");
        return new ResponseEntity<>(ObjectMapperUtils.map(userAppService.currentUser(),UserDto.class), HttpStatus.OK);
    }


    /**
     * @param id the id of user to show his comments
     * @return response entity status 404 if user is not found
     *  or status 200 and the comments of user on body
     */
    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getCommentsByUser(@PathVariable Long id) {
        try {
            UserApp userApp = userAppService.findById(id);
            if (userApp == null)
                throw new Exception("USER_NOT_FOUND");
            return ResponseEntity.ok(userAppService.getCommentByUser(userApp));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * @return response entity status 200 (ok) and the current user's post on body
     */
    @GetMapping("/posts")
    public ResponseEntity<?> getPostByUser() {
        try {
            return ResponseEntity.ok(ObjectMapperUtils.mapAll(userAppService.getPostByCurrentUser(), PostDto.class));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * @param userRegistrationDto the body of user to create
     * @return response entity status 409 if the id its on body, if the username already exist,
     * status 403 if the password its not valid.
     * or status 201 if user created and user on body.
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {

        try {
            if (userRegistrationDto.getId() != null)
                return new ResponseEntity<Object>(new Exception("Please remove the Id"), HttpStatus.CONFLICT);

            if (userAppService.findUserByUsername(userRegistrationDto.getUsername().toLowerCase()) != null)
                return new ResponseEntity<Object>(new Exception("Username already exist"), HttpStatus.CONFLICT);

            if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getRePassword()))
                return new ResponseEntity<Object>(new Exception("Please check your password"), HttpStatus.FORBIDDEN);

            UserApp result = userAppService.createUser(ObjectMapperUtils.map(userRegistrationDto,UserApp.class));
            return new ResponseEntity<>(ObjectMapperUtils.map(result, userRegistrationDto), HttpStatus.CREATED);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * @param id the id of user to update
     * @param userDto the body of user to update
     * @return response entity status 403 if user not exist
     * or status 201 if user are updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {

        try {

            if (userAppService.findById(id) == null)
                return new ResponseEntity<Object>(new Exception("This user not exist"), HttpStatus.FORBIDDEN);
            userDto.setId(id);
            UserApp result = userAppService.updateUser(ObjectMapperUtils.map(userDto, UserApp.class));
            return new ResponseEntity<>(ObjectMapperUtils.map(result, userDto), HttpStatus.CREATED);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * @param id the id of user to delete
     * @return response entity 200 if user are deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            if (userAppService.findById(id) == null)
                throw new Exception("This user not exist");
            userAppService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("delete post : {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
