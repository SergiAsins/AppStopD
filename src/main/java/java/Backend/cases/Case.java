package java.Backend.cases;

import java.Backend.status.Status;
import java.Backend.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
//import lombok.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotNull(message = "The status cannot be null")
    private Status status; // Enum: PENDING, IN_PROGRESS, RESOLVED

    @NotNull(message = "The address cannot be null")
    private String address;

    @NotNull(message = "The region cannot be null")
    private String region;

    @NotNull(message = "The city cannot be null")
    private String city;

    @NotNull(message = "The Date cannot be null")
    @Future(message = "A Eviction Case must be noticed in advance.")
    @Column(name = "eviction_time", nullable = false)
    private Timestamp dateReported;

    @ManyToMany
    @JoinTable(
            name = "case_tenants",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "tenant", nullable = false)
    private Set<User> tenants;

    private String description;

    public Case(){
    }

    // Complete Constructor
    public Case(Status status, String address, String region, String city, Timestamp dateReported, Set<User> tenants, String description) {
        this.status = status;
        this.address = address;
        this.region = region;
        this.city = city;
        this.dateReported = dateReported;
        this.tenants = tenants;
        this.description = description;
    }

    // Simplified Constructor
    public Case(Status status,  String address, String region, String city, Timestamp dateReported, String description) {
        this(status, address, region, city, dateReported, new HashSet<>(), description);
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

    public @NotNull(message = "The Date cannot be null") @Future(message = "A Eviction Case must be noticed in advance.") Timestamp getDateReported() {
        return dateReported;
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

    public void setDateReported(@NotNull(message = "The Date cannot be null") @Future(message = "A Eviction Case must be noticed in advance.") Timestamp dateReported) {
        this.dateReported = dateReported;
    }

}
