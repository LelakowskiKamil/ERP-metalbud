package com.lelakowski.ERPMetalbud.common.domain.dimension.repository;

import com.lelakowski.ERPMetalbud.common.domain.dimension.model.Dimensions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DimensionsRepository extends JpaRepository<Dimensions, Long> {

}