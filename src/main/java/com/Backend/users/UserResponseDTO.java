package com.Backend.users;

import com.Backend.roles.Role;

import java.util.Set;

public record UserResponseDTO(
        Long id,
        Set<Role> roles,
        String name
){
}
