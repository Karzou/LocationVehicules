package com.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */
 
@Entity
@Table(name = "roles", schema = "location_vehicules")
@NamedQuery(name = "Role.lister", query = "SELECT c FROM Role c, Autorise a, Permission p GROUP BY c.idRole ORDER BY c.idRole")
public class Role {
    private int idRole;
    private String roleDescription;
    private List<Autorise> autorisesByIdRole;
    private List<Utilisateur> utilisateursByIdRole;

    public Role(){}

    public Role(String roleDescription){
        this.roleDescription = roleDescription;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_role", nullable = false)
    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    @Basic
    @Column(name = "Role_description", nullable = false, length = 50)
    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return idRole == role.idRole && Objects.equals(roleDescription, role.roleDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, roleDescription);
    }

    @OneToMany(mappedBy = "rolesByIdRole")
    public List<Autorise> getAutorisesByIdRole() {
        return autorisesByIdRole;
    }

    public void setAutorisesByIdRole(List<Autorise> autorisesByIdRole) {
        this.autorisesByIdRole = autorisesByIdRole;
    }

    @OneToMany(mappedBy = "rolesByIdRole")
    public List<Utilisateur> getUtilisateursByIdRole() {
        return utilisateursByIdRole;
    }

    public void setUtilisateursByIdRole(List<Utilisateur> utilisateursByIdRole) {
        this.utilisateursByIdRole = utilisateursByIdRole;
    }
}
