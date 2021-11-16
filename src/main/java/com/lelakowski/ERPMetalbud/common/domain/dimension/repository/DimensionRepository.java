package com.lelakowski.ERPMetalbud.common.domain.dimension.repository;

import com.lelakowski.ERPMetalbud.common.domain.dimension.model.Dimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DimensionRepository extends JpaRepository<Dimension, Long> {

}