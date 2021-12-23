package com.lelakowski.ERPMetalbud.pi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product_specification")
public class ProductSpecification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "externalName", nullable = false, unique = true)
    String externalName;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "dimensions_id")
    Dimensions dimensions;


    public ProductSpecification(String externalName, Dimensions dimensions) {
        this.externalName = externalName;
        this.dimensions = dimensions;
    }

}
