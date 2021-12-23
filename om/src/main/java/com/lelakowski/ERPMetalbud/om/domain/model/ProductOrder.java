package com.lelakowski.ERPMetalbud.om.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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

    @Setter
    @Column(name = "orderDate", nullable = false)
    @Pattern(regexp = "^([0-9]{4})-([0-1][0-9])-([0-3]((?<!3)[0-9]|[0-1]))\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$")
    private String orderDate;

    @Setter
    @Column(name = "confirmDate")
    @Pattern(regexp = "^([0-9]{4})-([0-1][0-9])-([0-3]((?<!3)[0-9]|[0-1]))\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$")
    private String confirmDate;

    @Setter
    @Column(name = "shipmentDate")
    @Pattern(regexp = "^([0-9]{4})-([0-1][0-9])-([0-3]((?<!3)[0-9]|[0-1]))\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$")
    private String shipmentDate;

    @Setter
    @Column(name = "closeDate")
    @Pattern(regexp = "^([0-9]{4})-([0-1][0-9])-([0-3]((?<!3)[0-9]|[0-1]))\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$")
    private String closeDate;

    @Setter
    @Column(name = "revertDate")
    @Pattern(regexp = "^([0-9]{4})-([0-1][0-9])-([0-3]((?<!3)[0-9]|[0-1]))\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$")
    private String revertDate;

    @Setter
    @Column(name = "customerId", nullable = false)
    private Long customerId;

    @Setter
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @OneToMany(mappedBy = "productOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ProductOrderItem> productOrderItems;

    public final void addToProductOrderItemList(ProductOrderItem productOrderItem) {
        productOrderItems.add(productOrderItem);
    }

}
