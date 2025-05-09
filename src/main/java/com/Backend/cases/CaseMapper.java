package com.Backend.cases;

import com.Backend.users.UserMapper;
import com.Backend.users.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

    public class CaseMapper {
        public static Case toEntity(CaseRequestDTO caseRequestDTO, User tenant ) {
            Set<User> tenants = new HashSet<>(caseRequestDTO.tenants()); //Tenants of the DTO not will be null anymore
            Set<User> attendants = new HashSet<>(caseRequestDTO.attendants());
            tenants.add(tenant); //Add the authenticatedUser as tenant
            return new Case(
                    caseRequestDTO.status(),
                    caseRequestDTO.address(),
                    caseRequestDTO.region(),
                    caseRequestDTO.city(),
                    caseRequestDTO.caseDate(),
                    tenants,
                    attendants,
                    caseRequestDTO.description(),
                    caseRequestDTO.urlImage()
            );
        }

    public static CaseResponseDTO toResponseDTO(Case caseEntity) {
        return new CaseResponseDTO(
                caseEntity.getId(),
                caseEntity.getStatus(),
                caseEntity.getAddress(),
                caseEntity.getRegion(),
                caseEntity.getCity(),
                caseEntity.getCaseDate(),
                caseEntity.getDescription(),
                caseEntity.getUrlImage(),
                caseEntity.getTenants().stream()
                        .map(UserMapper::toResponseDTO)
                        .collect(Collectors.toSet()),
                caseEntity.getAttendants().stream()
                        .map(UserMapper::toResponseDTO)
                        .collect(Collectors.toSet())
        );
    }
}

