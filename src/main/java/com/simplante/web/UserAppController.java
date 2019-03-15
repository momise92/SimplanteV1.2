package com.simplante.web;


import com.simplante.dto.UserRegistrationDto;
import com.simplante.dto.mapper.UserMapper;
import com.simplante.model.UserApp;
import com.simplante.service.CommentService;
import com.simplante.service.UserAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserAppController {

    private UserAppService userAppService;
    private UserMapper userMapper;
    private CommentService commentService;

    public UserAppController(UserAppService userAppService, UserMapper userMapper, CommentService commentService) {
        this.userAppService = userAppService;
        this.userMapper = userMapper;
        this.commentService = commentService;
    }


    /**
     * @return
     */
    @GetMapping
    public ResponseEntity<?> listUsers() {
        log.debug("get All users");
        return new ResponseEntity<>(userMapper.listusersToListRegistrationDto(userAppService.ListUsers()), HttpStatus.OK);
    }

    /**
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            if (userAppService.findById(id) == null)
                return new ResponseEntity<Object>(new Exception("User not exist"), HttpStatus.NOT_FOUND);

            log.debug("Get user by ID");
            return new ResponseEntity<>(userAppService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Find user by id : ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

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
     * @return
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {

        try {
            if (userRegistrationDto.getId() != null)
                return new ResponseEntity<Object>(new Exception("Please remove the Id"), HttpStatus.UNAUTHORIZED);

            if (userAppService.findUserByUsername(userRegistrationDto.getUsername().toLowerCase()) != null)
                return new ResponseEntity<Object>(new Exception("Username already exist"), HttpStatus.CONFLICT);

            if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getRePassword()))
                return new ResponseEntity<Object>(new Exception("Please check your password"), HttpStatus.FORBIDDEN);

            UserApp result = userAppService.createUser(userMapper.registrationDtoToUser(userRegistrationDto));
            /*userAppService.addRoleToUser(userRegistrationDto.getUsername(),"User");*/
            return new ResponseEntity<>(userMapper.userToRegistrationDto(result), HttpStatus.CREATED);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?>updateUser(@PathVariable Long id, @RequestBody @Valid  UserRegistrationDto userRegistrationDto) {

        try {

            if (userAppService.findById(id) == null)
                return new ResponseEntity<>("This user not exist", HttpStatus.FORBIDDEN);
            userRegistrationDto.setId(id);
            UserApp result = userAppService.updateUser(userMapper.registrationDtoToUser(userRegistrationDto));
            return new ResponseEntity<>(userMapper.userToRegistrationDto(result), HttpStatus.CREATED);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteUser(@PathVariable Long id){
        try {
            if (userAppService.findById(id) == null)
                throw new Exception("This user not exist");
            userAppService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("delete post :", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
