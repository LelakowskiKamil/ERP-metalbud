package com.lelakowski.ERPMetalbud.pim.domain.model;


import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import com.lelakowski.ERPMetalbud.pim.domain.model.Account;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @NotNull
    @Size(min = 5, max = 50)
    @Column(name = "name")
    String name;

    @NotNull
    @Size(min = 5, max = 50)
    @Column(name = "surname")
    String surname;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    Account account;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    Address address;

    public Address getAddress() {
        return address;
    }

    public Account getAccount() {
        return account;
    }


    public Customer(Long id, String name, String surname, Account account, Address address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.account = account;
        this.address = address;
    }
}
