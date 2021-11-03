package com.lelakowski.ERPMetalbud.pe.domain.model;

import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "value")
    private double value;

    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "currency")
    private String currency;

    @OneToMany(mappedBy = "salaryGross")
    private List<Employee> employees;

    public Price(Long id, double value, String currency, List<Employee> employees) {
        this.id = id;
        this.value = value;
        this.currency = currency;
        this.employees = employees;
    }

    public Price() {
    }

    public Long getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
