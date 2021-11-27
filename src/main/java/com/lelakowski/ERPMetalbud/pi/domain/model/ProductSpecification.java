package com.lelakowski.ERPMetalbud.pi.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimensions;
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
@Table(name = "product_specification")
public class ProductSpecification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    String caption;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "dimensions_id")
    Dimensions dimensions;
    @JsonBackReference
    @OneToMany(mappedBy = "productSpecification", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ProductDetails> productDetails;

    public final void addToProductDetailsList(ProductDetails productDetailReference){
        productDetails.add(productDetailReference);
    }

}
