package com.lelakowski.ERPMetalbud.pim.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @JsonBackReference
    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Customer> customers;

    public final void addToCustomerList(Customer customerReference) {
        customers.add(customerReference);
    }

}
