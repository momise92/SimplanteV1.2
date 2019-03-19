package com.simplante.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class Credential {


    @NotEmpty(message = "*Please provide your name")
    private String username;

    @Length(min = 5, message = "*Your password must have at least 5 characters")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
