package com.lelakowski.ERPMetalbud.common.dimension.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "dimensions")
@ToString
public class Dimensions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    String caption;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "height_id")
    Dimension height;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "width_id")
    Dimension width;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "length_id")
    Dimension length;


    public Dimensions(String caption, Dimension height, Dimension width, Dimension length) {
        this.caption = caption;
        this.height = height;
        this.width = width;
        this.length = length;
    }
}
