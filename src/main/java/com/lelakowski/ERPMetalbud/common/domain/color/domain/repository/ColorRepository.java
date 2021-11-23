package com.lelakowski.ERPMetalbud.common.domain.color.domain.repository;

import com.lelakowski.ERPMetalbud.common.domain.color.domain.model.Color;
import com.lelakowski.ERPMetalbud.common.domain.dimension.domain.model.Dimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    @Query(value = "SELECT c FROM Color c WHERE c.oem = ?1" )
    Color getColorByOEM(String oem);

}