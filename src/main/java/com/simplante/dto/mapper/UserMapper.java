package com.simplante.dto.mapper;

import com.simplante.dto.UserDto;
import com.simplante.model.UserApp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    ModelMapper modelMapper;

    public UserApp userDtoToUser(UserDto dto) {
        return modelMapper.map(dto, UserApp.class);
    }

    public UserDto userToUserDto(UserApp entity) {
        return modelMapper.map(entity, UserDto.class);
    }

    public List<UserDto> listusersToListUserDto(List<UserApp> users) {
        return users.stream().map(userApp -> userToUserDto(userApp)).collect(Collectors.toList());
    }
}
