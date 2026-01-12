package com.nafiu.moviereservationservice.auth.mapper;

import com.nafiu.moviereservationservice.auth.dto.UserRegistrationDto;
import com.nafiu.moviereservationservice.auth.dto.UserResponseDto;
import com.nafiu.moviereservationservice.auth.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto userToUserResponseDto(User user){
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getRole()
        );
    }

    public User userFromUserRegistrationDto(UserRegistrationDto userRegistrationDto, String role){
        return new User(
                userRegistrationDto.name(),
                userRegistrationDto.username(),
                userRegistrationDto.password(),
                role
        );
    }
}
