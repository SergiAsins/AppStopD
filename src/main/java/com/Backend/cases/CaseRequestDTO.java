package com.Backend.cases;

import com.Backend.cases.status.Status;
import com.Backend.users.User;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public record CaseRequestDTO (

        @NotEmpty(message = "The region cannot be null")
        @NotEmpty(message = "The region cannot be empty")
        String region,

        @NotEmpty(message = "The city cannot be null")
        @NotEmpty(message = "The city cannot be empty")
        String city,

        @NotNull(message = "The address cannot be null")
        @NotEmpty(message = "The address cannot be empty")
        String address,

        Status status,

        @NotNull(message = "The Date cannot be null")
        @Future(message = "A Eviction Case must be noticed in advance.")
        LocalDate caseDate,

        String description,

        //@NotNull(message = "The tenants cannot be null")
        //@NotEmpty(message = "The tenants cannot be empty")
        Set<User> tenants,

        Set<User> attendants

)
{
        public CaseRequestDTO {
                tenants = tenants != null ? tenants : new HashSet<>();
                attendants = attendants != null ? attendants : new HashSet<>();
        }
}

