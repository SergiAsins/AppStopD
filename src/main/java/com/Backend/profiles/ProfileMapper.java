package com.Backend.profiles;

import com.Backend.users.User;
import jakarta.validation.Valid;

public class ProfileMapper {
    public static Profile toEntity(@Valid ProfileRequestDTO profileRequestDTO, User user) {
        return new Profile(
                profileRequestDTO.name(),
                profileRequestDTO.phone(),
                profileRequestDTO.email(),
                profileRequestDTO.address(),
                profileRequestDTO.picture(),
                user
        );
    }

    public static ProfileResponseDTO toResponse(Profile profile){
        return new ProfileResponseDTO(
                profile.getId(),
                profile.getName(),
                profile.getPhone(),
                profile.getEmail(),
                profile.getAddress(),
                profile.getPicture(),
                profile.getUser()
        );
    }
}
