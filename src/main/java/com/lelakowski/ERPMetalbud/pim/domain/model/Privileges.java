package com.lelakowski.ERPMetalbud.pim.domain.model;

import com.lelakowski.ERPMetalbud.pim.domain.model.Account;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "privileges")
public class Privileges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "caption")
    private String caption;

    @NotNull
    @Column(name = "can_view")
    private Boolean canView;

    @NotNull
    @Column(name = "can_create")
    private Boolean canCreate;

    @NotNull
    @Column(name = "can_update")
    private Boolean canUpdate;

    @NotNull
    @Column(name = "can_remove")
    private Boolean canRemove;

    @OneToMany(mappedBy = "privileges")
    private List<Account> accounts;

    public Privileges() {
    }

    public Privileges(Long id, String caption, Boolean canView, Boolean canCreate, Boolean canUpdate, Boolean canRemove, List<Account> accounts) {
        this.id = id;
        this.caption = caption;
        this.canView = canView;
        this.canCreate = canCreate;
        this.canUpdate = canUpdate;
        this.canRemove = canRemove;
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Privileges{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", canView=" + canView +
                ", canCreate=" + canCreate +
                ", canUpdate=" + canUpdate +
                ", canRemove=" + canRemove +
                ", accounts=" + accounts +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public Boolean getCanView() {
        return canView;
    }

    public Boolean getCanCreate() {
        return canCreate;
    }

    public Boolean getCanUpdate() {
        return canUpdate;
    }

    public Boolean getCanRemove() {
        return canRemove;
    }


}
