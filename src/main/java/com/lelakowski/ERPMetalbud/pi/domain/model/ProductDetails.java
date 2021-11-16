package com.lelakowski.ERPMetalbud.pi.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lelakowski.ERPMetalbud.common.domain.color.domain.Color;
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
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "genus_id")
    Genus genus;

    @JsonBackReference
    @OneToMany(mappedBy = "productDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

}
