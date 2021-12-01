package com.lelakowski.ERPMetalbud.pe.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lelakowski.ERPMetalbud.pi.domain.model.Product;
import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import lombok.*;

import javax.persistence.*;
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

    @JsonBackReference
    @OneToMany(mappedBy = "salaryGross", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Employee> employees;

    @JsonBackReference
    @OneToMany(mappedBy = "price", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    public final void addToProductList(Product product) {
        products.add(product);
    }

    public final void addToEmployeeList(Employee employeeReference) {
        employees.add(employeeReference);
    }

}
