/*package com.simplante.web;

import com.simplante.dao.UserAppRepository;
import com.simplante.dto.Credential;
import com.simplante.model.UserApp;
import com.simplante.security.JWTAuthentificationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
@Slf4j
public class AccountController {

    private UserAppRepository userAppRepository;
    private PasswordEncoder passwordEncoder;
    private JWTAuthentificationFilter jwtAuthentificationFilter;


    public AccountController(UserAppRepository userAppRepository, PasswordEncoder passwordEncoder, JWTAuthentificationFilter jwtAuthentificationFilter) {
        this.userAppRepository = userAppRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthentificationFilter = jwtAuthentificationFilter;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody Credential cred, HttpServletRequest request)
            throws Exception {

        UserApp user = userAppRepository.findByUsername(cred.getUsername());

        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bad User");

        if (!user.getIsActive())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Your account is disabled");

        if (!passwordEncoder.matches(cred.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bad password");
        }


        String jwt = jwtAuthentificationFilter
        return ResponseEntity.ok().body(user);

    }
}*/
