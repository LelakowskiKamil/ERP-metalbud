package com.lelakowski.ERPMetalbud.om.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product_order")
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date orderDate;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    @JsonBackReference
    @OneToMany(mappedBy = "productOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductOrderItem> productOrderItems;

    public final void addToProductOrderItemList(ProductOrderItem productOrderItem) {
        productOrderItems.add(productOrderItem);
    }
}
