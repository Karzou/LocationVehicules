package com.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */
@Entity
@Table(name = "villes", schema = "location_vehicules")
@NamedQueries({
@NamedQuery(name = "Ville.trouverParNomVille", query = "SELECT v FROM Ville v WHERE v.nomVille = :nomVille "),
@NamedQuery(name = "Ville.lister", query= "SELECT c FROM Ville c ORDER BY c.idVille"),})
public class Ville {
    private int idVille;
    private String nomVille;
    private String codePostal;
    private List<Adresse> adressesByIdVille;
    private Pays paysByIdPays;

    public Ville(){}

    public Ville(String nomVille, String codePostal, Pays pays){
        this.nomVille = nomVille;
        this.codePostal = codePostal;
        this.paysByIdPays = pays;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_ville", nullable = false)
    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    @Basic
    @Column(name = "Nom_ville", nullable = false, length = 100)
    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    @Basic
    @Column(name = "Code_postal", nullable = false, length = 10)
    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ville ville = (Ville) o;
        return idVille == ville.idVille && Objects.equals(nomVille, ville.nomVille) && Objects.equals(codePostal, ville.codePostal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVille, nomVille, codePostal);
    }

    @OneToMany(mappedBy = "villesByIdVille")
    public List<Adresse> getAdressesByIdVille() {
        return adressesByIdVille;
    }

    public void setAdressesByIdVille(List<Adresse> adressesByIdVille) {
        this.adressesByIdVille = adressesByIdVille;
    }

    @ManyToOne
    @JoinColumn(name = "Id_pays")
    public Pays getPaysByIdPays() {
        return paysByIdPays;
    }

    public void setPaysByIdPays(Pays paysByIdPays) {
        this.paysByIdPays = paysByIdPays;
    }
}
