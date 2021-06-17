package com.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */
 
@Entity
@Table(name = "options_supplementaires", schema = "location_vehicules")
public class OptionSupplementaire {
    private int idOptionSup;
    private String nomOptionSup;
    private float prixOptionSup;
    private boolean actifOptionSup;
    private int quantiteOptionSup;
    private List<Choisir> choisirsByIdOptionSup;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_option_sup", nullable = false)
    public int getIdOptionSup() {
        return idOptionSup;
    }

    public void setIdOptionSup(int idOptionSup) {
        this.idOptionSup = idOptionSup;
    }

    @Basic
    @Column(name = "Nom_option_sup", nullable = false, length = 50)
    public String getNomOptionSup() {
        return nomOptionSup;
    }

    public void setNomOptionSup(String nomOptionSup) {
        this.nomOptionSup = nomOptionSup;
    }

    @Basic
    @Column(name = "Prix_option_sup", nullable = false, precision = 2)
    public float getPrixOptionSup() {
        return prixOptionSup;
    }

    public void setPrixOptionSup(float prixOptionSup) {
        this.prixOptionSup = prixOptionSup;
    }

    @Basic
    @Column(name = "Actif_option_sup", nullable = false)
    public boolean isActifOptionSup() {
        return actifOptionSup;
    }

    public void setActifOptionSup(boolean actifOptionSup) {
        this.actifOptionSup = actifOptionSup;
    }

    @Basic
    @Column(name = "Quantite_option_sup", nullable = false)
    public int getQuantiteOptionSup() {
        return quantiteOptionSup;
    }

    public void setQuantiteOptionSup(int quantiteOptionSup) {
        this.quantiteOptionSup = quantiteOptionSup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionSupplementaire that = (OptionSupplementaire) o;
        return idOptionSup == that.idOptionSup && actifOptionSup == that.actifOptionSup && quantiteOptionSup == that.quantiteOptionSup && Objects.equals(nomOptionSup, that.nomOptionSup) && Objects.equals(prixOptionSup, that.prixOptionSup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOptionSup, nomOptionSup, prixOptionSup, actifOptionSup, quantiteOptionSup);
    }

    @OneToMany(mappedBy = "optionsSupplementairesByIdOptionSup")
    public List<Choisir> getChoisirsByIdOptionSup() {
        return choisirsByIdOptionSup;
    }

    public void setChoisirsByIdOptionSup(List<Choisir> choisirsByIdOptionSup) {
        this.choisirsByIdOptionSup = choisirsByIdOptionSup;
    }
}
