package com.lelakowski.ERPMetalbud.pi.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "dimension")
@ToString
public class Dimension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "value", nullable = false)
    Double value;

    @Column(name = "unit", nullable = false)
    String unit;


    public Dimension(Double value, String unit) {
        this.value = value;
        this.unit = unit;
    }
}
