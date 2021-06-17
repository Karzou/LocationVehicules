package com.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */
 
@Entity
public class Pays {
    private int idPays;
    private String nomPays;
    private List<Ville> villeByIdPays;

  /* Pour l'instant on ne prévoit pas l'insert de nouveau pays
    public Pays(){}

    public Pays(String nomPays){
        this.nomPays = nomPays;
    }
*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_pays", nullable = false)
    public int getIdPays() {
        return idPays;
    }

    public void setIdPays(int idPays) {
        this.idPays = idPays;
    }

    @Basic
    @Column(name = "Nom_pays", nullable = false, length = 100)
    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pays pays = (Pays) o;
        return idPays == pays.idPays && Objects.equals(nomPays, pays.nomPays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPays, nomPays);
    }

    @OneToMany(mappedBy = "paysByIdPays")
    public List<Ville> getVillesByIdPays() {
        return villeByIdPays;
    }

    public void setVillesByIdPays(List<Ville> villeByIdPays) {
        this.villeByIdPays = villeByIdPays;
    }
}
