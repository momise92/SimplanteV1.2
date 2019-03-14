package com.simplante.dto.mapper;

import com.simplante.dto.CategoryDto;
import com.simplante.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    @Autowired
    ModelMapper modelMapper;

    public Category DtoToCategory(CategoryDto dto) {
        return modelMapper.map(dto, Category.class);
    }

    public CategoryDto categoryToDto(Category entity) {
        return modelMapper.map(entity, CategoryDto.class);
    }

    public List<CategoryDto> CategoriesToListDto(List<Category> categories) {
        return categories.stream().map(this::categoryToDto).collect(Collectors.toList());

    }
}
