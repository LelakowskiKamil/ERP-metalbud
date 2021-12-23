package com.lelakowski.ERPMetalbud.pi.domain.repository;

import com.lelakowski.ERPMetalbud.pi.domain.model.Dimensions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DimensionsRepository extends JpaRepository<Dimensions, Long> {
    @Query(value = "SELECT d.id FROM Dimensions d WHERE d.caption = ?1")
    Optional<Long> findDimensionByCaptionName(String caption);
}