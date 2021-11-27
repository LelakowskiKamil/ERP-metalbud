package com.lelakowski.ERPMetalbud.common.dimension.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductSpecification;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "dimensions")
@ToString
public class Dimensions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    String caption;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "height_id")
    Dimension height;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "width_id")
    Dimension width;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "length_id")
    Dimension length;

    @JsonBackReference
    @OneToMany(mappedBy = "dimensions", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ProductSpecification> productSpecifications;

    public final void addToProductSpecificationList(ProductSpecification productSpecificationReference){
        productSpecifications.add(productSpecificationReference);
    }

}
