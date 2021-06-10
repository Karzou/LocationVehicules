package com.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */
@Entity
@Table(name = "reservations", schema = "location_vehicules")
public class Reservation {
    private int idReservation;
    private Date dateDebutLocation;
    private Time heureDebutLocation;
    private String lieuDebutLocation;
    private Date dateFinLocation;
    private Time heureFinLocation;
    private String lieuFinLocation;
    private List<Choisir> choisirsByIdReservation;
    private Paiement paiementsByIdPaiement;
    private Contrat contratsByIdContrat;
    private Utilisateur utilisateursByIdUtilisateur;
    private Vehicule vehiculesByIdVehicule;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_reservation", nullable = false)
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    @Basic
    @Column(name = "Date_debut_location", nullable = false)
    public Date getDateDebutLocation() {
        return dateDebutLocation;
    }

    public void setDateDebutLocation(Date dateDebutLocation) {
        this.dateDebutLocation = dateDebutLocation;
    }

    @Basic
    @Column(name = "Heure_debut_location", nullable = false)
    public Time getHeureDebutLocation() {
        return heureDebutLocation;
    }

    public void setHeureDebutLocation(Time heureDebutLocation) {
        this.heureDebutLocation = heureDebutLocation;
    }

    @Basic
    @Column(name = "Lieu_debut_location", nullable = false, length = 50)
    public String getLieuDebutLocation() {
        return lieuDebutLocation;
    }

    public void setLieuDebutLocation(String lieuDebutLocation) {
        this.lieuDebutLocation = lieuDebutLocation;
    }

    @Basic
    @Column(name = "Date_fin_location", nullable = false)
    public Date getDateFinLocation() {
        return dateFinLocation;
    }

    public void setDateFinLocation(Date dateFinLocation) {
        this.dateFinLocation = dateFinLocation;
    }

    @Basic
    @Column(name = "Heure_fin_location", nullable = false)
    public Time getHeureFinLocation() {
        return heureFinLocation;
    }

    public void setHeureFinLocation(Time heureFinLocation) {
        this.heureFinLocation = heureFinLocation;
    }

    @Basic
    @Column(name = "Lieu_fin_location", nullable = false, length = 50)
    public String getLieuFinLocation() {
        return lieuFinLocation;
    }

    public void setLieuFinLocation(String lieuFinLocation) {
        this.lieuFinLocation = lieuFinLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return idReservation == that.idReservation && Objects.equals(dateDebutLocation, that.dateDebutLocation) && Objects.equals(heureDebutLocation, that.heureDebutLocation) && Objects.equals(lieuDebutLocation, that.lieuDebutLocation) && Objects.equals(dateFinLocation, that.dateFinLocation) && Objects.equals(heureFinLocation, that.heureFinLocation) && Objects.equals(lieuFinLocation, that.lieuFinLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReservation, dateDebutLocation, heureDebutLocation, lieuDebutLocation, dateFinLocation, heureFinLocation, lieuFinLocation);
    }

    @OneToMany(mappedBy = "reservationsByIdReservation")
    public List<Choisir> getChoisirsByIdReservation() {
        return choisirsByIdReservation;
    }

    public void setChoisirsByIdReservation(List<Choisir> choisirsByIdReservation) {
        this.choisirsByIdReservation = choisirsByIdReservation;
    }

    @ManyToOne
    @JoinColumn(name = "Id_paiement", referencedColumnName = "Id_paiement", nullable = false)
    public Paiement getPaiementsByIdPaiement() {
        return paiementsByIdPaiement;
    }

    public void setPaiementsByIdPaiement(Paiement paiementsByIdPaiement) {
        this.paiementsByIdPaiement = paiementsByIdPaiement;
    }

    @ManyToOne
    @JoinColumn(name = "Id_contrat", referencedColumnName = "Id_contrat", nullable = false)
    public Contrat getContratsByIdContrat() {
        return contratsByIdContrat;
    }

    public void setContratsByIdContrat(Contrat contratsByIdContrat) {
        this.contratsByIdContrat = contratsByIdContrat;
    }

    @ManyToOne
    @JoinColumn(name = "Id_utilisateur", referencedColumnName = "Id_utilisateur", nullable = false)
    public Utilisateur getUtilisateursByIdUtilisateur() {
        return utilisateursByIdUtilisateur;
    }

    public void setUtilisateursByIdUtilisateur(Utilisateur utilisateursByIdUtilisateur) {
        this.utilisateursByIdUtilisateur = utilisateursByIdUtilisateur;
    }

    @ManyToOne
    @JoinColumn(name = "Id_vehicule", referencedColumnName = "Id_vehicule", nullable = false)
    public Vehicule getVehiculesByIdVehicule() {
        return vehiculesByIdVehicule;
    }

    public void setVehiculesByIdVehicule(Vehicule vehiculesByIdVehicule) {
        this.vehiculesByIdVehicule = vehiculesByIdVehicule;
    }
}
