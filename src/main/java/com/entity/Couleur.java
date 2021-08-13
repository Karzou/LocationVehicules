package com.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */
 
@Entity
@Table(name = "couleurs", schema = "location_vehicules")
@NamedQueries({
        @NamedQuery(name = "Couleur.checkCouleurExist", query = "SELECT COUNT(c) FROM Couleur c WHERE c.nomCouleur = :nomCouleur"),
        @NamedQuery(name = "Couleur.trouverParNom", query = "SELECT c FROM Couleur c WHERE c.nomCouleur = :nomCouleur"),
        @NamedQuery(name = "Couleur.lister", query = "SELECT c FROM Couleur c GROUP BY c.nomCouleur"),
})
public class Couleur {
    private int idCouleur;
    private String nomCouleur;
    private boolean actifCouleur;
    private List<Vehicule> vehiculesByIdCouleur;

    public Couleur() {

    }

    public Couleur(String nomCouleur) {

        this.nomCouleur = nomCouleur;
        this.actifCouleur = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_couleur", nullable = false)
    public int getIdCouleur() {
        return idCouleur;
    }

    public void setIdCouleur(int idCouleur) {
        this.idCouleur = idCouleur;
    }

    @Basic
    @Column(name = "Nom_couleur", nullable = false, length = 50)
    public String getNomCouleur() {
        return nomCouleur;
    }

    public void setNomCouleur(String nomCouleur) {
        this.nomCouleur = nomCouleur;
    }

    @Basic
    @Column(name = "Actif_couleur", nullable = false)
    public boolean isActifCouleur() {
        return actifCouleur;
    }

    public void setActifCouleur(boolean actifCouleur) {
        this.actifCouleur = actifCouleur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Couleur couleur = (Couleur) o;
        return idCouleur == couleur.idCouleur && actifCouleur == couleur.actifCouleur && Objects.equals(nomCouleur, couleur.nomCouleur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCouleur, nomCouleur, actifCouleur);
    }

    @OneToMany(mappedBy = "couleursByIdCouleur")
    public List<Vehicule> getVehiculesByIdCouleur() {
        return vehiculesByIdCouleur;
    }

    public void setVehiculesByIdCouleur(List<Vehicule> vehiculesByIdCouleur) {
        this.vehiculesByIdCouleur = vehiculesByIdCouleur;
    }
}
