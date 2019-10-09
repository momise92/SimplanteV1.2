package com.simplante;

import com.simplante.dto.PostDto;
import com.simplante.dto.mapper.ObjectMapperUtils;
import com.simplante.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMapper {


    @Test
    public void testPostMapper() {

        // Entity to DTO
        Post post = new Post("Test Mapper", "Je test le mapper");
        post.setId(1L);
        assertEquals("Test Mapper", ObjectMapperUtils.map(post, PostDto.class).getTitle());
        assertEquals(1L, (long) ObjectMapperUtils.map(post, PostDto.class).getId());
    }
}
