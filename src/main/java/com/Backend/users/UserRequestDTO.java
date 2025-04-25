package com.Backend.users;

import com.Backend.roles.Role;
import jakarta.validation.constraints.*;

public record UserRequestDTO (

        //Role role,

        @NotNull(message = "Username cannot be null")
        @NotEmpty(message = "Username cannot be empty")
        String username,

        @NotNull(message = "Password cannot be null")
        @NotEmpty(message = "Password cannot be empty")
        String password
){
}
