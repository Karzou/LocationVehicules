package com.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */
 
@Entity
@Table(name = "telephones", schema = "location_vehicules")
@NamedQueries({
        @NamedQuery(name = "Telephone.lister", query = "SELECT t FROM Telephone t WHERE t.utilisateursByIdUtilisateur = :utilisateur"),
})
public class Telephone {
    private int idTelephone;
    private String numero;
    private Utilisateur utilisateursByIdUtilisateur;

    public Telephone() {}

    public Telephone(String numero){
        this.numero = numero;
    }

    public Telephone(String numero, Utilisateur utilisateur){
        this.numero = numero;
        this.utilisateursByIdUtilisateur = utilisateur;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_telephone", nullable = false)
    public int getIdTelephone() {
        return idTelephone;
    }

    public void setIdTelephone(int idTelephone) {
        this.idTelephone = idTelephone;
    }

    @Basic
    @Column(name = "Numero", nullable = false, length = 50)
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telephone telephone = (Telephone) o;
        return idTelephone == telephone.idTelephone && Objects.equals(numero, telephone.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTelephone, numero);
    }

    @ManyToOne
    @JoinColumn(name = "Id_utilisateur", referencedColumnName = "Id_utilisateur", nullable = false)
    public Utilisateur getUtilisateursByIdUtilisateur() {
        return utilisateursByIdUtilisateur;
    }

    public void setUtilisateursByIdUtilisateur(Utilisateur utilisateursByIdUtilisateur) {
        this.utilisateursByIdUtilisateur = utilisateursByIdUtilisateur;
    }
}
