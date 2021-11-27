package com.lelakowski.ERPMetalbud.pim.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "profession")
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "designation")
    private String designation;

    @JsonBackReference
    @OneToMany(mappedBy = "profession", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Employee> employees;

    public final void addToEmployeeList(Employee employeeReference){
        employees.add(employeeReference);
    }


}
