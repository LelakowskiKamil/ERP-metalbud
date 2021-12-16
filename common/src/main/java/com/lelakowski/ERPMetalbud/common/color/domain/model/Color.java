package com.lelakowski.ERPMetalbud.common.color.domain.model;

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
    String oem;
    String caption;

    public Color(String oem, String caption) {
        this.oem = oem;
        this.caption = caption;
    }
}
