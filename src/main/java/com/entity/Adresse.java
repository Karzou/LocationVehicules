package com.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */
 
@Entity
@Table(name = "adresses", schema = "location_vehicules")
@NamedQuery(name = "Adresse.lister", query = "SELECT c FROM Adresse c ORDER BY c.idAdresse")
public class Adresse {
    private int idAdresse;
    private String rue;
    private String numero;
    private String boite;
    private Ville villeByIdVille;
    private List<Entrepot> entrepotsByIdAdresse;
    private List<Utilisateur> utilisateursByIdAdresse;

    public Adresse(){

    }

    public Adresse(String rue, String numero, String boite, Ville ville){
        this.rue = rue;
        this.numero = numero;
        this.boite = boite;
        this.villeByIdVille = ville;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_adresse", nullable = false)
    public int getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(int idAdresse) {
        this.idAdresse = idAdresse;
    }

    @Basic
    @Column(name = "Rue", nullable = false, length = 100)
    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    @Basic
    @Column(name = "Numero", nullable = false, length = 10)
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Basic
    @Column(name = "Boite", nullable = true, length = 10)
    public String getBoite() {
        return boite;
    }

    public void setBoite(String boite) {
        this.boite = boite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresse = (Adresse) o;
        return idAdresse == adresse.idAdresse && Objects.equals(rue, adresse.rue) && Objects.equals(numero, adresse.numero) && Objects.equals(boite, adresse.boite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdresse, rue, numero, boite);
    }

    @ManyToOne
    @JoinColumn(name = "Id_ville", referencedColumnName = "Id_ville", nullable = false)
    public Ville getVillesByIdVille() {
        return villeByIdVille;
    }

    public void setVillesByIdVille(Ville villeByIdVille) {
        this.villeByIdVille = villeByIdVille;
    }

    @OneToMany(mappedBy = "adressesByIdAdresse")
    public List<Entrepot> getEntrepotsByIdAdresse() {
        return entrepotsByIdAdresse;
    }

    public void setEntrepotsByIdAdresse(List<Entrepot> entrepotsByIdAdresse) {
        this.entrepotsByIdAdresse = entrepotsByIdAdresse;
    }

    @OneToMany(mappedBy = "adressesByIdAdresse")
    public List<Utilisateur> getUtilisateursByIdAdresse() {
        return utilisateursByIdAdresse;
    }

    public void setUtilisateursByIdAdresse(List<Utilisateur> utilisateursByIdAdresse) {
        this.utilisateursByIdAdresse = utilisateursByIdAdresse;
    }
}
