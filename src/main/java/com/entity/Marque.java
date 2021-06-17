package com.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */

@Entity
@Table(name = "marques", schema = "location_vehicules")
@NamedQueries({
        @NamedQuery(name = "Marque.trouverParNom", query = "SELECT ma FROM Marque ma WHERE ma.nomMarque = :nomMarque"),
        @NamedQuery(name = "Marque.lister", query = "SELECT ma FROM Marque ma"),
})
public class Marque {
    private int idMarque;
    private String nomMarque;
    private List<Modele> modelesByIdMarque;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_marque", nullable = false)
    public int getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }

    @Basic
    @Column(name = "Nom_marque", nullable = false, length = 50)
    public String getNomMarque() {
        return nomMarque;
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marque marque = (Marque) o;
        return idMarque == marque.idMarque && Objects.equals(nomMarque, marque.nomMarque);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMarque, nomMarque);
    }

    @OneToMany(mappedBy = "marquesByIdMarque")
    public List<Modele> getModelesByIdMarque() {
        return modelesByIdMarque;
    }

    public void setModelesByIdMarque(List<Modele> modelesByIdMarque) {
        this.modelesByIdMarque = modelesByIdMarque;
    }
}
