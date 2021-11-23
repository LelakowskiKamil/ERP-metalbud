package com.lelakowski.ERPMetalbud.common.domain.dimension.domain.repository;

import com.lelakowski.ERPMetalbud.common.domain.dimension.domain.model.Dimensions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DimensionsRepository extends JpaRepository<Dimensions, Long> {

}