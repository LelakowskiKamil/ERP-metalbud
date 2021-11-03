package com.lelakowski.ERPMetalbud.pim.domain.model;

import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "profession")
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 2, max = 255)
    @Column(name = "designation")
    private String designation;

    @OneToMany(mappedBy = "profession")
    private List<Employee> employees;
}
