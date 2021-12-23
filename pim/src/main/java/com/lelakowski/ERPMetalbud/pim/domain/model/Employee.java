package com.lelakowski.ERPMetalbud.pim.domain.model;


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

    @Column(name = "externalName", nullable = false, unique = true)
    private String externalName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "profession_id", referencedColumnName = "id")
    @ToString.Exclude
    private Profession profession;

    @Column(name = "employment_date", nullable = false)
    private String employmentDate;

    @Column(name = "salaryId", nullable = false)
    private Long salaryId;

    public Employee(String externalName, String email, Profession profession, String employmentDate, Long salaryId) {
        this.externalName = externalName;
        this.email = email;
        this.profession = profession;
        this.employmentDate = employmentDate;
        this.salaryId = salaryId;
    }
}
