package com.Backend.cases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public interface CaseRepository extends JpaRepository<Case, Long>{
    @Query("SELECT c FROM Case c WHERE c.status = :status")
    List<Case> findByStatus(@Param("status") String status);

    @Query("SELECT c FROM Case c WHERE c.caseDate = :caseDate")
    List<Case> findByCaseDate(@Param("caseDate") LocalDate caseDate);

    @Query("SELECT c FROM Case c WHERE c.city = :city")
    List<Case> findByCity(@Param("city") String city);

    @Query("SELECT c FROM Case c WHERE c.region = :region")
    List<Case> findByRegion(@Param("region") String region);
}
