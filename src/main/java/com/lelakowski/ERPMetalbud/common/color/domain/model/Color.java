package com.lelakowski.ERPMetalbud.common.color.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductDetails;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "color")
@ToString
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    String oem;
    String caption;
    @JsonBackReference
    @OneToMany(mappedBy = "color", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ProductDetails> productDetails;

    public final void addToProductDetailsList(ProductDetails productDetailReference) {
        productDetails.add(productDetailReference);
    }

    public Color(String oem, String caption, List<ProductDetails> productDetails) {
        this.oem = oem;
        this.caption = caption;
        this.productDetails = productDetails;
    }
}
