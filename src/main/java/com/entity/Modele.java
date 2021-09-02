package com.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */
 
@Entity
@Table(name = "modeles", schema = "location_vehicules")
@NamedQueries({
        @NamedQuery(name = "Modele.checkModeleExist", query = "SELECT COUNT(mo) FROM Modele mo WHERE mo.nomModele = :nomModele"),
        @NamedQuery(name = "Modele.trouverParNom", query = "SELECT mo FROM Modele mo WHERE mo.nomModele = :nomModele"),
        @NamedQuery(name = "Modele.lister", query = "SELECT mo FROM Modele mo GROUP BY mo.nomModele"),
})
public class Modele {
    private int idModele;
    private String nomModele;
    private boolean actifModele;
    private Marque marquesByIdMarque;
    private List<Vehicule> vehiculesByIdModele;

    public Modele() {

    }

    public Modele(String nomModele, Marque marque) {

        this.nomModele = nomModele;
        this.marquesByIdMarque = marque;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_modele", nullable = false)
    public int getIdModele() {
        return idModele;
    }

    public void setIdModele(int idModele) {
        this.idModele = idModele;
    }

    @Basic
    @Column(name = "Nom_modele", nullable = false, length = 50)
    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }

    @Basic
    @Column(name = "Actif_modele", nullable = false)
    public boolean isActifModele() {
        return actifModele;
    }

    public void setActifModele(boolean actifModele) {
        this.actifModele = actifModele;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modele modele = (Modele) o;
        return idModele == modele.idModele && actifModele == modele.actifModele && Objects.equals(nomModele, modele.nomModele);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idModele, nomModele, actifModele);
    }

    @ManyToOne
    @JoinColumn(name = "Id_marque", referencedColumnName = "Id_marque", nullable = false)
    public Marque getMarquesByIdMarque() {
        return marquesByIdMarque;
    }

    public void setMarquesByIdMarque(Marque marquesByIdMarque) {
        this.marquesByIdMarque = marquesByIdMarque;
    }

    @OneToMany(mappedBy = "modelesByIdModele")
    public List<Vehicule> getVehiculesByIdModele() {
        return vehiculesByIdModele;
    }

    public void setVehiculesByIdModele(List<Vehicule> vehiculesByIdModele) {
        this.vehiculesByIdModele = vehiculesByIdModele;
    }
}
