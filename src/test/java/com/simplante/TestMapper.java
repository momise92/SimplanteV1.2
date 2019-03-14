package com.simplante;

import com.simplante.dto.PostDto;
import com.simplante.dto.mapper.PostMapper;
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

    @Autowired
    PostMapper postMapper;

    @Test
    public void testPostMapper() {

        // Entity to DTO
        Post post = new Post("Test Mapper", "Je test le mapper");
        post.setId(1L);
        assertEquals("Test Mapper", postMapper.postToPostDto(post).getTitle());
        assertEquals(1L, (long) postMapper.postToPostDto(post).getId());

        // DTO to entity
        PostDto postDto = new PostDto();
        postDto.setTitle("Test MapperDto");
        postDto.setBody("Abracadabra");
        postDto.setId(1L);
        assertTrue(postMapper.postDtoToPost(postDto).getTitle().equals("Test MapperDto"));
        assertTrue(postMapper.postDtoToPost(postDto).getId() == 1L);
    }


}
