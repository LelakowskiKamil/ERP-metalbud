package com.lelakowski.ERPMetalbud.common.domain.dimension.domain.model;

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
@Table(name = "dimension")
public class Dimension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    Double value;
    String unit;

    @JsonBackReference
    @OneToMany(mappedBy = "height", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dimensions> dimensionsHeight;

    @JsonBackReference
    @OneToMany(mappedBy = "width", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dimensions> dimensionsWidth;

    @JsonBackReference
    @OneToMany(mappedBy = "length", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dimensions> dimensionsLength;

}
