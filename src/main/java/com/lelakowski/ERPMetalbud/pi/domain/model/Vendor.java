package com.lelakowski.ERPMetalbud.pi.domain.model;

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
@Table(name = "vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    String caption;

    @JsonBackReference
    @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;
}
