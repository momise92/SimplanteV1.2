package com.simplante;

import com.simplante.dao.CategoryRepository;
import com.simplante.dao.CommentRepository;
import com.simplante.dao.PostRepository;
import com.simplante.model.Category;
import com.simplante.model.Comment;
import com.simplante.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;


    @Test
    public void TestCategory() {
        Category c = new Category("comique");
        c.addPost(new Post("Title", "Somebody test category"));
        categoryRepository.save(c);

        assertNotNull(postRepository.findByCategoryId(1L));


    }

    @Test
    public void TestPost() {
        Post p = new Post();
        p.setTitle("comique And Pub");
        p.setBody("Body of post");

        Category c = new Category("blague");
        categoryRepository.save(c);

        /*p.setCategoryId(1L);*/
        postRepository.save(p);

        assertNotNull(postRepository.findByTitle("comique And Pub"));

        assertNotNull(postRepository.findByCategory(c));


        postRepository.deleteAll();
    }

    @Test
    public void testComment() {
        //findByProject
        Post post = postRepository.save(new Post("Title", "Welcome"));
        post.addComment(new Comment("Pas mal !!"));
        post.addComment(new Comment("in progress"));
        postRepository.save(post);

        assertEquals(2, commentRepository.findByPost(post).size());

        postRepository.deleteAll();
    }


}
