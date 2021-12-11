package com.lelakowski.ERPMetalbud.pim.domain.model;

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
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "privileges_id")
    private Privileges privileges;

    @JsonBackReference
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Customer> customers;

    public final void addToCustomerList(Customer customerReference) {
        customers.add(customerReference);
    }

    public Account(String username, String password, String email, Privileges privileges, List<Customer> customers) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.privileges = privileges;
        this.customers = customers;
    }
}
