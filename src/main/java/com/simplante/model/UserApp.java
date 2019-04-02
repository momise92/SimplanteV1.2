package com.simplante.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Moïse Coulanges
 * @version 0.1
 */
@Entity
public class UserApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "register_date", nullable = false, updatable = false)
    private LocalDateTime registerDate = LocalDateTime.now();

    @Column(unique = true, nullable = false)
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "password", nullable = false)
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "username", nullable = false, unique = true)
    @Length(min = 4, max = 20, message = "*Your username must have at least 4 characters")
    @NotEmpty(message = "*Please provide your username")
    private String username;

    @NotEmpty(message = "*Please provide your firstName")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "*Please provide your last lastName")
    private String lastName;

    @Column(nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Post> posts = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleApp> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<>();

    public UserApp() {

    }

    public UserApp(String email, String password, String username, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setUserId(this.getId());
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setUserId(null);
    }

    public void removeAllComments() {
        for (Comment comment : this.comments) {
            removeComment(comment);
        }
    }

    public void addPost(Post post) {
        this.posts.add(post);
        post.setUserId(this.getId());
    }

    public void removePost(Post post) {
        this.posts.remove(post);
        post.setUserId(null);
    }

    public void removeAllPosts() {
        for (Post post : this.posts) {
            removePost(post);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<RoleApp> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleApp> roles) {
        this.roles = roles;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserApp )) return false;
        return id != null && id.equals(((UserApp) o).getId());
    }
    @Override
    public int hashCode() {
        return 31;
    }
}
