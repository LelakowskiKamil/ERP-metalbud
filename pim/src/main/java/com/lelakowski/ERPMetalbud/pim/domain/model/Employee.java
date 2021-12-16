package com.lelakowski.ERPMetalbud.pim.domain.model;


import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "profession_id", referencedColumnName = "id")
    @ToString.Exclude
    private Profession profession;

    @Column(name = "employment_date")
    private String employmentDate;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "salary_gross_id", referencedColumnName = "id")
    @ToString.Exclude
    private Price salaryGross;

    public Employee(String email, Profession profession, String employmentDate, Price salaryGross) {
        this.email = email;
        this.profession = profession;
        this.employmentDate = employmentDate;
        this.salaryGross = salaryGross;
    }
}
