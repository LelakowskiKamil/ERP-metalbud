package com.lelakowski.ERPMetalbud.common.domain.color.repository;

import com.lelakowski.ERPMetalbud.common.domain.color.domain.Color;
import com.lelakowski.ERPMetalbud.common.domain.dimension.model.Dimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

}