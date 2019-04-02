package com.simplante.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simplante.model.RoleApp;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

public class UserDto {

    private Long id;

    @Email(message = "*Please provide a valid Email")
    private String email;

    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String rePassword;

    @NotEmpty(message = "*Please provide your firstName")
    private String username;

    @NotEmpty(message = "*Please provide your firstName")
    private String firstName;

    @NotEmpty(message = "*Please provide your last firstName")
    private String lastName;

    private LocalDateTime registerDate;

    private Boolean isActive;


    private List<RoleApp> roles;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List getRolesRole() {
        return roles;
    }

    public void setRolesRole(List roles) {
        this.roles = roles;
    }

}
