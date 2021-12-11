package com.lelakowski.ERPMetalbud.pim.domain.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @JsonBackReference
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductOrder> productOrderList;

    public final void addToProductOrderList(ProductOrder productOrder) {
        productOrderList.add(productOrder);
    }

    public Customer(String name, String surname, Account account, Address address, List<ProductOrder> productOrderList) {
        this.name = name;
        this.surname = surname;
        this.account = account;
        this.address = address;
        this.productOrderList = productOrderList;
    }
}
