package com.simplante.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Moïse Coulanges
 * @version 0.1
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name", unique = true, length = 50)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Post> posts = new HashSet<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }


    public void addPost(Post post) {
        this.posts.add(post);
        post.setCategory(this);
    }

    public void removePost(Post post) {
        this.posts.remove(post);
        post.setCategory(null);
    }

    public List<Post> getpost() {
        return new ArrayList<>(this.posts);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
