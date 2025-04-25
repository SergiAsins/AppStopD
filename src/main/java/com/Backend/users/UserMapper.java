package com.Backend.users;

import java.util.Set;

public class UserMapper {
    public static User toEntity(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setUsername(userRequestDTO.username());
        user.setPassword(userRequestDTO.password());
        //user.setRoles(Set.of(userRequestDTO.role()));
        return user;
    }

    public static UserResponseDTO toResponseDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                //user.getRoles(),
                user.getUsername(),
                user.getPassword()
        );
    }
}
