package com.lelakowski.ERPMetalbud.pi.domain.model;

import com.lelakowski.ERPMetalbud.common.color.domain.model.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product_details")
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "color_id")
    Color color;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_specification_id")
    ProductSpecification productSpecification;


    public ProductDetails(Color color, ProductSpecification productSpecification) {
        this.color = color;
        this.productSpecification = productSpecification;
    }
}
