package com.lelakowski.ERPMetalbud.mi.domain.model;

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
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String caption;
    @JsonBackReference
    @OneToMany(mappedBy = "material", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BillOfMaterialItem> billOfMaterialItems;

    public final void addToBillOfMaterialItemList(BillOfMaterialItem billOfMaterialItem) {
        billOfMaterialItems.add(billOfMaterialItem);
    }

}
