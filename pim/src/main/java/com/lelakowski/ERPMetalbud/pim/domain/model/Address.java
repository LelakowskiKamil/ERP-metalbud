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

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "postalCode", nullable = false)
    private String postalCode;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;


    public Address(String city, String postalCode, String state, String country) {
        this.city = city;
        this.postalCode = postalCode;
        this.state = state;
        this.country = country;
    }
}
