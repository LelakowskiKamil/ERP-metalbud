package com.lelakowski.ERPMetalbud.pim.domain.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 2, max = 255)
    @Column(name = "city")
    private String city;

    @NotNull
    @Size(min = 5, max = 30)
    @Column(name = "postal_code")
    private String postalCode;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(name = "state")
    private String state;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "address")
    private List<Customer> customers;

    public Address(Long id, String city, String postalCode, String state, String country, List<Customer> customers) {
        this.id = id;
        this.city = city;
        this.postalCode = postalCode;
        this.state = state;
        this.country = country;
        this.customers = customers;
    }

    public Address() {
    }
}
