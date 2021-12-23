package com.lelakowski.ERPMetalbud.pi.domain.model;

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
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "externalName", nullable = false, unique = true)
    private String externalName;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_details_id")
    private ProductDetails productDetails;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "priceId", nullable = false)
    private Long priceId;

    public Product(String externalName, ProductDetails productDetails, Vendor vendor, Brand brand, Long priceId) {
        this.externalName = externalName;
        this.productDetails = productDetails;
        this.vendor = vendor;
        this.brand = brand;
        this.priceId = priceId;
    }
}
