package com.lelakowski.ERPMetalbud.om.domain.model;

import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
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
    @Pattern(regexp = "^([0-9]{4})-([0-1][0-9])-([0-3]((?<!3)[0-9]|[0-1]))\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$")
    private String orderDate;
    @Setter
    @Pattern(regexp = "^([0-9]{4})-([0-1][0-9])-([0-3]((?<!3)[0-9]|[0-1]))\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$")
    private String confirmDate;
    @Setter
    @Pattern(regexp = "^([0-9]{4})-([0-1][0-9])-([0-3]((?<!3)[0-9]|[0-1]))\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$")
    private String shipmentDate;
    @Setter
    @Pattern(regexp = "^([0-9]{4})-([0-1][0-9])-([0-3]((?<!3)[0-9]|[0-1]))\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$")
    private String closeDate;
    @Setter
    @Pattern(regexp = "^([0-9]{4})-([0-1][0-9])-([0-3]((?<!3)[0-9]|[0-1]))\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$")
    private String revertDate;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @Setter
    private Customer customer;
    @Setter
    private OrderStatus status;

    @OneToMany(mappedBy = "productOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ProductOrderItem> productOrderItems;

    public final void addToProductOrderItemList(ProductOrderItem productOrderItem) {
        productOrderItems.add(productOrderItem);
    }

}
