package com.simplante.dto.mapper;

import com.simplante.dto.UserRegistrationDto;
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

    public UserApp registrationDtoToUser(UserRegistrationDto dto) {
        return modelMapper.map(dto, UserApp.class);
    }

    public UserRegistrationDto userToRegistrationDto(UserApp entity) {
        return modelMapper.map(entity, UserRegistrationDto.class);
    }

    public List<UserRegistrationDto> listusersToListRegistrationDto(List<UserApp> users) {
        return users.stream().map(userApp -> userToRegistrationDto(userApp)).collect(Collectors.toList());
    }
}
