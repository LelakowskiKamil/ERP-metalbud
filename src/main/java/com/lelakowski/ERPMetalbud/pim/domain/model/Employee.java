package com.lelakowski.ERPMetalbud.pim.domain.model;



import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

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
    @JoinColumn(name = "profession_id",referencedColumnName = "id")
    @ToString.Exclude
    private Profession profession;

    @Column(name = "employment_date")
    private  Date employmentDate;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "salary_gross_id",referencedColumnName = "id")
    @ToString.Exclude
    private Price salaryGross;

}
