package com.lelakowski.ERPMetalbud.pim.domain.model;

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
@Table(name = "profession")
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "designation")
    private String designation;

    @OneToMany(mappedBy = "profession")
    @ToString.Exclude
    private List<Employee> employees;

}
