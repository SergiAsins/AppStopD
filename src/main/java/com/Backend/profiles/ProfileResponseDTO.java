package com.Backend.profiles;

import com.Backend.users.User;

public record ProfileResponseDTO (
        Long id,
        String name,
        String phone,
        String email,
        String address,
        String picture,
        User user
    ){
}
