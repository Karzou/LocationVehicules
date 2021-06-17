package com.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */

@Entity
@Table(name = "entrepots", schema = "location_vehicules")
@NamedQueries({
        @NamedQuery(name = "Entrepot.trouverParNom", query = "SELECT e FROM Entrepot e WHERE e.nomEntrepot = :nom"),
        @NamedQuery(name = "Entrepot.lister", query = "SELECT e FROM Entrepot e JOIN e.adressesByIdAdresse a JOIN a.villesByIdVille v"),
})
public class Entrepot {
    private int idEntrepot;
    private String nomEntrepot;
    private int nombrePlace;
    private boolean actifEntrepot;
    private Adresse adressesByIdAdresse;
    private List<Vehicule> vehiculesByIdEntrepot;

    public Entrepot() {

    }

    public Entrepot(String nomEntrepot, int nombrePlace, Adresse adresse) {

        this.nomEntrepot = nomEntrepot;
        this.nombrePlace = nombrePlace;
        this.actifEntrepot = true;
        this.adressesByIdAdresse = adresse;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_entrepot", nullable = false)
    public int getIdEntrepot() {
        return idEntrepot;
    }

    public void setIdEntrepot(int idEntrepot) {
        this.idEntrepot = idEntrepot;
    }

    @Basic
    @Column(name = "Nom_entrepot", nullable = false, length = 50)
    public String getNomEntrepot() {
        return nomEntrepot;
    }

    public void setNomEntrepot(String nomEntrepot) {
        this.nomEntrepot = nomEntrepot;
    }

    @Basic
    @Column(name = "Nombre_place", nullable = false)
    public int getNombrePlace() {
        return nombrePlace;
    }

    public void setNombrePlace(int nombrePlace) {
        this.nombrePlace = nombrePlace;
    }

    @Basic
    @Column(name = "Actif_entrepot", nullable = false)
    public boolean isActifEntrepot() {
        return actifEntrepot;
    }

    public void setActifEntrepot(boolean actifEntrepot) {
        this.actifEntrepot = actifEntrepot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrepot entrepot = (Entrepot) o;
        return idEntrepot == entrepot.idEntrepot && nombrePlace == entrepot.nombrePlace && actifEntrepot == entrepot.actifEntrepot;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEntrepot, nombrePlace, actifEntrepot);
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Id_adresse", referencedColumnName = "Id_adresse", nullable = false)
    public Adresse getAdressesByIdAdresse() {
        return adressesByIdAdresse;
    }

    public void setAdressesByIdAdresse(Adresse adressesByIdAdresse) {
        this.adressesByIdAdresse = adressesByIdAdresse;
    }

    @OneToMany(mappedBy = "entrepotsByIdEntrepot")
    public List<Vehicule> getVehiculesByIdEntrepot() {
        return vehiculesByIdEntrepot;
    }

    public void setVehiculesByIdEntrepot(List<Vehicule> vehiculesByIdEntrepot) {
        this.vehiculesByIdEntrepot = vehiculesByIdEntrepot;
    }
}
