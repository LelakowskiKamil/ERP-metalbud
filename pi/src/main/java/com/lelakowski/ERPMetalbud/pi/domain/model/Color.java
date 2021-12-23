package com.lelakowski.ERPMetalbud.pi.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "color")
@ToString
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "externalName", nullable = false, unique = true)
    String externalName;

    @Column(name = "oem", nullable = false, unique = true)
    String oem;


    public Color(String oem, String externalName) {
        this.oem = oem;
        this.externalName = externalName;
    }
}
