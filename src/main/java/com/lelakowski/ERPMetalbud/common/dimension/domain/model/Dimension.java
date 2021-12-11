package com.lelakowski.ERPMetalbud.common.dimension.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    public final void addToDimensionsHeightList(Dimensions dimensionsReference) {
        dimensionsHeight.add(dimensionsReference);
    }

    public final void addToDimensionsWidthList(Dimensions dimensionsReference) {
        dimensionsWidth.add(dimensionsReference);
    }

    public final void addToDimensionsLengthList(Dimensions dimensionsReference) {
        dimensionsLength.add(dimensionsReference);
    }

    public Dimension(Double value, String unit, List<Dimensions> dimensionsHeight, List<Dimensions> dimensionsWidth, List<Dimensions> dimensionsLength) {
        this.value = value;
        this.unit = unit;
        this.dimensionsHeight = dimensionsHeight;
        this.dimensionsWidth = dimensionsWidth;
        this.dimensionsLength = dimensionsLength;
    }
}
