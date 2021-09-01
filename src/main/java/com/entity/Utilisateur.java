package com.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */
 
@Entity
@Table(name = "utilisateurs", schema = "location_vehicules")
@NamedQueries({
        @NamedQuery(name = "Utilisateur.mailExist", query = "SELECT u FROM Utilisateur u WHERE u.email = :email"),
        @NamedQuery(name = "Utilisateur.checkLogin", query = "SELECT u FROM Utilisateur u WHERE u.email = :email AND u.motDePasse = :password AND u.actifUtilisateur=true"),
        @NamedQuery(name = "Utilisateur.trouverParEmail", query = "SELECT u FROM Utilisateur u WHERE u.email = :email"),
        @NamedQuery(name = "Utilisateur.trouverParNom", query = "SELECT u FROM Utilisateur u WHERE u.nomUtilisateur = :nom"),
        @NamedQuery(name = "Utilisateur.lister", query = "SELECT u FROM Utilisateur u JOIN u.adressesByIdAdresse a JOIN a.villesByIdVille v ORDER BY u.nomUtilisateur"),
})
public class Utilisateur {
    private int idUtilisateur;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String email;
    private String motDePasse;
    private String telephone;
    private Date dateNaissance;
    private Date datePermis;
    private boolean actifUtilisateur;
    private List<Reservation> reservationsByIdUtilisateur;
    private Adresse adressesByIdAdresse;
    private Role rolesByIdRole;

    public Utilisateur(){}

    public Utilisateur(String nomUtilisateur, String prenomUtilisateur, String email, String telephone, String motDePasse, Date dateNaissance,
                       Date datePermis, Adresse adresse, Role role){
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.email = email;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
        this.dateNaissance = dateNaissance;
        this.datePermis = datePermis;
        this.actifUtilisateur = true;
        this.adressesByIdAdresse = adresse;
        this.rolesByIdRole = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_utilisateur", nullable = false)
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Basic
    @Column(name = "Nom_utilisateur", nullable = false, length = 50)
    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    @Basic
    @Column(name = "Prenom_utilisateur", nullable = false, length = 50)
    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    @Basic
    @Column(name = "Email", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Mot_de_passe", nullable = false, length = 255)
    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Basic
    @Column(name = "Telephone", nullable = false, length = 50)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    @Basic
    @Column(name = "Date_naissance", nullable = false)
    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Basic
    @Column(name = "Date_permis", nullable = false)
    public Date getDatePermis() {
        return datePermis;
    }

    public void setDatePermis(Date datePermis) {
        this.datePermis = datePermis;
    }

    @Basic
    @Column(name = "Actif_utilisateur", nullable = false)
    public boolean isActifUtilisateur() {
        return actifUtilisateur;
    }

    public void setActifUtilisateur(boolean actifUtilisateur) {
        this.actifUtilisateur = actifUtilisateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return idUtilisateur == that.idUtilisateur && actifUtilisateur == that.actifUtilisateur && Objects.equals(nomUtilisateur, that.nomUtilisateur) && Objects.equals(prenomUtilisateur, that.prenomUtilisateur) && Objects.equals(email, that.email) && Objects.equals(motDePasse, that.motDePasse) && Objects.equals(dateNaissance, that.dateNaissance) && Objects.equals(datePermis, that.datePermis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur, nomUtilisateur, prenomUtilisateur, email, motDePasse, dateNaissance, datePermis, actifUtilisateur);
    }

    @OneToMany(mappedBy = "utilisateursByIdUtilisateur")
    public List<Reservation> getReservationsByIdUtilisateur() {
        return reservationsByIdUtilisateur;
    }

    public void setReservationsByIdUtilisateur(List<Reservation> reservationsByIdUtilisateur) {
        this.reservationsByIdUtilisateur = reservationsByIdUtilisateur;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Id_adresse", referencedColumnName = "Id_adresse", nullable = false)
    public Adresse getAdressesByIdAdresse() {
        return adressesByIdAdresse;
    }

    public void setAdressesByIdAdresse(Adresse adressesByIdAdresse) {
        this.adressesByIdAdresse = adressesByIdAdresse;
    }

    @ManyToOne
    @JoinColumn(name = "Id_role", referencedColumnName = "Id_role", nullable = false)
    public Role getRolesByIdRole() {
        return rolesByIdRole;
    }

    public void setRolesByIdRole(Role rolesByIdRole) {
        this.rolesByIdRole = rolesByIdRole;
    }
}
