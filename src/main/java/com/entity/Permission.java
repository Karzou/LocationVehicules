package com.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */
 
@Entity
@Table(name = "permissions", schema = "location_vehicules")
public class Permission {
    private int idPermission;
    private String nomPermission;
    private List<Autorise> autorisesByIdPermission;

    public Permission(){}

    public Permission(String nomPermission){
        this.nomPermission = nomPermission;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_permission", nullable = false)
    public int getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(int idPermission) {
        this.idPermission = idPermission;
    }

    @Basic
    @Column(name = "Nom_permission", nullable = false, length = 50)
    public String getNomPermission() {
        return nomPermission;
    }

    public void setNomPermission(String nomPermission) {
        this.nomPermission = nomPermission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return idPermission == that.idPermission && Objects.equals(nomPermission, that.nomPermission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPermission, nomPermission);
    }

    @OneToMany(mappedBy = "permissionsByIdPermission")
    public List<Autorise> getAutorisesByIdPermission() {
        return autorisesByIdPermission;
    }

    public void setAutorisesByIdPermission(List<Autorise> autorisesByIdPermission) {
        this.autorisesByIdPermission = autorisesByIdPermission;
    }
}
