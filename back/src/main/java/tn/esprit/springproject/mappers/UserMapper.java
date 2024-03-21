package tn.esprit.springproject.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.springproject.Dto.SignUpDto;
import tn.esprit.springproject.Dto.UserDto;
import tn.esprit.springproject.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    @Mapping(target = "password",ignore = true)
    User SignUptoUser(SignUpDto signUpDto);
}
