package com.simplante.dto.mapper;

import com.simplante.dto.PostDto;
import com.simplante.model.Post;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostMapper {

    @Autowired
    ModelMapper modelMapper;

    public Post postDtoToPost(PostDto dto) {
        /*modelMapper.getConfiguration().setAmbiguityIgnored(true);*/
        return modelMapper.map(dto, Post.class);
    }

    public PostDto postToPostDto(Post entity) {

        return modelMapper.map(entity, PostDto.class);
    }

    public List<PostDto> listPostsToListPostsDto(List<Post> posts) {
        return posts.stream().map(post -> postToPostDto(post)).collect(Collectors.toList());
    }

}
