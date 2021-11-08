package com.lelakowski.ERPMetalbud.pim.domain.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "privileges")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Privileges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "caption")
    private String caption;

    @Column(name = "can_view")
    private Boolean canView;

    @Column(name = "can_create")
    private Boolean canCreate;

    @Column(name = "can_update")
    private Boolean canUpdate;

    @Column(name = "can_remove")
    private Boolean canRemove;

    @OneToMany(mappedBy = "privileges")
    @ToString.Exclude
    private List<Account> accounts;


}
