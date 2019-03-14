package com.simplante.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Post {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false, unique=true)
    @Length(min = 5, max = 100, message = "*Your title must have at least 5 characters")
    private String title;

    @Column(nullable = false)
    private String body;


    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate = LocalDateTime.now();


    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    private int vote;

    @Column(nullable = false)
    private Boolean status = false;

    private Long userId;

    @ManyToOne
    @JoinColumn(name ="userId", insertable = false, updatable = false)
    private UserApp user;

    private Long categoryId;

    @ManyToOne
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    public Post(){

    }

    public Post(@Length(min = 5, max = 100, message = "*Your title must have at least 5 characters") String title, String body) {
        this.title = title;
        this.body = body;
    }
    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setPostId(this.getId());
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setPostId(null);
    }

    public void removeAllComments()
    {
        for(Comment comment : this.comments)
        {
            removeComment(comment);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /*public Category getCategory() {
        return category;
    }*/

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
