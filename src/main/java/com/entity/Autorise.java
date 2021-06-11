package com.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */
@Entity
@NamedQueries({@NamedQuery(name = "Autorise.hasPermission", query = "SELECT a from Autorise a where a.rolesByIdRole.idRole = :idRole and a.permissionsByIdPermission.nomPermission = :permission"),
        @NamedQuery(name = "Autorise.listeParRole", query = "SELECT a from Autorise a where a.rolesByIdRole.idRole=:idRole")
        ,})
public class Autorise {
    private int idAutorise;
    private Permission permissionsByIdPermission;
    private Role rolesByIdRole;

    public Autorise(){}

    public Autorise(Permission permission, Role role){
        this.rolesByIdRole = role;
        this.permissionsByIdPermission = permission;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_autorise", nullable = false)
    public int getIdAutorise() {
        return idAutorise;
    }

    public void setIdAutorise(int idAutorise) {
        this.idAutorise = idAutorise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autorise autorise = (Autorise) o;
        return idAutorise == autorise.idAutorise;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAutorise);
    }

    @ManyToOne
    @JoinColumn(name = "Id_permission", referencedColumnName = "Id_permission", nullable = false)
    public Permission getPermissionsByIdPermission() {
        return permissionsByIdPermission;
    }

    public void setPermissionsByIdPermission(Permission permissionsByIdPermission) {
        this.permissionsByIdPermission = permissionsByIdPermission;
    }

    @ManyToOne
    @JoinColumn(name = "Id_role", referencedColumnName = "Id_role", nullable = false)
    public Role getRolesByIdRole() {
        return rolesByIdRole;
    }

    public void setRolesByIdRole(Role rolesByIdRole) {
        this.rolesByIdRole = rolesByIdRole;
    }
}
