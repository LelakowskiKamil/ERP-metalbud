package com.lelakowski.ERPMetalbud.pi.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String caption;

    @JsonBackReference
    @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    public final void addToProductList(Product product) {
        products.add(product);
    }

    public Vendor(String caption, List<Product> products) {
        this.caption = caption;
        this.products = products;
    }
}
