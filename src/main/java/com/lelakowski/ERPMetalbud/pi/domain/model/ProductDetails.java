package com.lelakowski.ERPMetalbud.pi.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lelakowski.ERPMetalbud.common.color.domain.model.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_specification_id")
    ProductSpecification productSpecification;

    @JsonBackReference
    @OneToMany(mappedBy = "productDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    public final void addToProductList(Product productReference) {
        products.add(productReference);
    }
}
