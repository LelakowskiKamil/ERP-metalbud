package com.lelakowski.ERPMetalbud.common.color.domain.repository;

import com.lelakowski.ERPMetalbud.common.color.domain.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    @Query(value = "SELECT c FROM Color c WHERE c.oem = ?1")
    Optional<Color> getColorByOEM(String oem);

}