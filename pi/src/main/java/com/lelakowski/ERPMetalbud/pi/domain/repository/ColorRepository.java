package com.lelakowski.ERPMetalbud.pi.domain.repository;

import com.lelakowski.ERPMetalbud.pi.domain.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    @Query(value = "SELECT c.id FROM Color c WHERE c.oem = ?1")
    Optional<Long> findColorByOEM(String oem);

    @Query(value = "SELECT c.id FROM Color c WHERE c.externalName = ?1")
    Optional<Long> findColorByExternalName(String externalName);
}