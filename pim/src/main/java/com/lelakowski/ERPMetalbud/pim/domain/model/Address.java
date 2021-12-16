package com.lelakowski.ERPMetalbud.pim.domain.model;

import lombok.*;

import javax.persistence.*;

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


    public Address(String city, String postalCode, String state, String country) {
        this.city = city;
        this.postalCode = postalCode;
        this.state = state;
        this.country = country;
    }
}
