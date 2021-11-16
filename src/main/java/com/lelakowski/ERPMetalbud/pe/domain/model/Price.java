package com.lelakowski.ERPMetalbud.pe.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lelakowski.ERPMetalbud.pi.domain.model.Product;
import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

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

    @OneToMany(mappedBy = "salaryGross", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Employee> employees;

    @JsonBackReference
    @OneToMany(mappedBy = "price", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

}
