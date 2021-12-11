package com.lelakowski.ERPMetalbud.pim.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "privileges")
@Getter
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

    @JsonBackReference
    @OneToMany(mappedBy = "privileges", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Account> accounts;

    public final void addToAccountList(Account accountReference) {
        accounts.add(accountReference);
    }

    public Privileges(String caption, Boolean canView, Boolean canCreate, Boolean canUpdate, Boolean canRemove, List<Account> accounts) {
        this.caption = caption;
        this.canView = canView;
        this.canCreate = canCreate;
        this.canUpdate = canUpdate;
        this.canRemove = canRemove;
        this.accounts = accounts;
    }
}
