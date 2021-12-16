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

    @Column(name = "value")
    private double value;

    @Column(name = "currency")
    private String currency;

    public Price(double value, String currency) {
        this.value = value;
        this.currency = currency;
    }
}
