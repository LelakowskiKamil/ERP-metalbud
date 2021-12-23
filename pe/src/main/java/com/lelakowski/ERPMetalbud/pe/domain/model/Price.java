package com.lelakowski.ERPMetalbud.pe.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "externalName", nullable = false, unique = true)
    private String externalName;

    @Column(name = "value", nullable = false)
    private double value;

    @Column(name = "currency", nullable = false)
    private String currency;

    public Price(String externalName, double value, String currency) {
        this.externalName = externalName;
        this.value = value;
        this.currency = currency;
    }
}
