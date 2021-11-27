package com.lelakowski.ERPMetalbud.common.dimension.domain.repository;

import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DimensionRepository extends JpaRepository<Dimension, Long> {

}