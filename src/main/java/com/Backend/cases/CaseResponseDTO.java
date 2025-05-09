package com.Backend.cases;

import com.Backend.cases.status.Status;
import com.Backend.users.UserResponseDTO;
import java.time.LocalDate;
import java.util.Set;

public record CaseResponseDTO (
        Long id,
        Status status,
        String address,
        String region,
        String city,
        LocalDate caseDate,
        String description,
        String urlImage,
        Set<UserResponseDTO> tenants,
        Set<UserResponseDTO> attendants
){
}
