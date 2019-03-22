package com.simplante;

import com.simplante.model.Post;
import com.simplante.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    PostService postService;

    @Test
    public void testPostService() throws Exception {

        /* create */
        Long id = postService.savePost(new Post("simplante1", "premier service")).getId();
        assertNotNull(postService.findByTitle("simplante1"));
        assertEquals("simplante1", postService.findById(id).getTitle());
        assertEquals(1, postService.listPost().size());

        assertThat(postService.findById(id)).isNotNull();

        /* update */
        Post post = postService.findById(id);
        post.setTitle("Simplante 1");
        postService.updatePost(post);

        assertEquals("Simplante 1", postService.findById(id).getTitle());

        assertEquals(1, postService.listPost().size());

        /* delete */
        postService.deletePost(id);
        assertEquals(0, postService.listPost().size());
    }
}
