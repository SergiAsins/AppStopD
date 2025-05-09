package com.Backend.cases;

import com.Backend.cases.status.Status;
import com.Backend.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "cases")
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotNull(message = "The status cannot be null")
    private Status status;

    @NotNull(message = "The address cannot be null")
    private String address;

    @NotNull(message = "The region cannot be null")
    private String region;

    @NotNull(message = "The city cannot be null")
    private String city;

    @NotNull(message = "The Date cannot be null")
    @Future(message = "A Eviction Case must be noticed in advance.")
    @Column(name = "case_date", nullable = false)
    private LocalDate caseDate;

    @ManyToMany
    @JoinTable(
            name = "case_tenants",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> tenants;

    @ManyToMany
    @JoinTable(
            name = "case_attendants",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> attendants = new HashSet<>(); //AI: Initialize to avoid NullPointerException

    private String description;

    @Column(name = "url_image")
    private String urlImage;

    // Default Constructor
    public Case(){
    }

    // Complete Constructor
    public Case(Status status, String address, String region, String city, LocalDate caseDate, Set<User> tenants, Set<User> attendants, String description , String urlImage) {
        this.status = status;
        this.address = address;
        this.region = region;
        this.city = city;
        this.caseDate = caseDate;
        this.tenants = tenants;
        this.attendants = attendants;
        this.description = description;
        this.urlImage = urlImage;
    }

    // Simplified Constructor
    public Case(Status status, String address, String region, String city, LocalDate caseDate, String description, String urlImage) {
        this(status, address, region, city, caseDate, new HashSet<>(), new HashSet<>(), description, urlImage);
    }

    public Case (Optional <Case> byId){
    }

    public Long getId() {
        return id;
    }

    public @NotNull(message = "The status cannot be null") Status getStatus() {
        return status;
    }

    public @NotNull(message = "The address cannot be null") String getAddress() {
        return address;
    }

    public @NotNull(message = "The region cannot be null") String getRegion() {
        return region;
    }

    public @NotNull(message = "The city cannot be null") String getCity() {
        return city;
    }

    public @NotNull(message = "The Date cannot be null") @Future(message = "A Eviction Case must be noticed in advance.") LocalDate getCaseDate() {
        return caseDate;
    }

    public Set<User> getAttendants() {
        return attendants;
    }

    public void setAttendants(Set<User> attendants) {
        this.attendants = attendants;
    }

    public String getDescription() {
        return description;
    }

    public Set<User> getTenants() {
        return tenants;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTenants(Set<User> tenants) {
        this.tenants = tenants;
    }

    public Set<User> getTenant() {
        return tenants;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(@NotNull(message = "The status cannot be null") Status status) {
        this.status = status;
    }

    public void setAddress(@NotNull(message = "The address cannot be null") String address) {
        this.address = address;
    }

    public void setRegion(@NotNull(message = "The region cannot be null") String region) {
        this.region = region;
    }

    public void setCity(@NotNull(message = "The city cannot be null") String city) {
        this.city = city;
    }

    public void setCaseDate(@NotNull(message = "The Date cannot be null") @Future(message = "A Eviction Case must be noticed in advance.") LocalDate caseDate) {
        this.caseDate = caseDate;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
