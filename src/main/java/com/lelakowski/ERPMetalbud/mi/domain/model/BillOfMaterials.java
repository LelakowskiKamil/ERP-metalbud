package com.lelakowski.ERPMetalbud.mi.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lelakowski.ERPMetalbud.pi.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
@Builder
//@Table(name = "bill_of_materials")
public class BillOfMaterials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
//    @JsonBackReference
//    @OneToMany(mappedBy = "billOfMaterials", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<BillOfMaterialItem> billOfMaterialItems;
//    @JsonBackReference
//    @OneToMany(mappedBy = "billOfMaterials",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Product> products;
}
