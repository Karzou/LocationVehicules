package com.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */

@Entity
@Table(name = "vehicules", schema = "location_vehicules")
@NamedQueries({
        @NamedQuery(name = "Vehicule.trouverParMarque", query = "SELECT v FROM Vehicule v WHERE v.modelesByIdModele.marquesByIdMarque.nomMarque = :marque"),
        @NamedQuery(name = "Vehicule.trouverParModele", query = "SELECT v FROM Vehicule v WHERE v.modelesByIdModele.nomModele = :modele"),
        @NamedQuery(name = "Vehicule.lister", query = "SELECT v FROM Vehicule v JOIN v.modelesByIdModele mo JOIN mo.marquesByIdMarque ma"),
        @NamedQuery(name = "Vehicule.rechercher", query = "SELECT v FROM Vehicule v JOIN v.modelesByIdModele mo JOIN mo.marquesByIdMarque ma WHERE v.entrepotsByIdEntrepot.idEntrepot = :idEntrepot AND NOT EXISTS (SELECT r FROM Reservation r WHERE r.vehiculesByIdVehicule.idVehicule = v.idVehicule AND r.dateDebutLocation <= :dateFin AND r.dateFinLocation >= :dateDebut)"),
})
public class Vehicule {
    private int idVehicule;
    private String numChassis;
    private int cylindree;
    private int puissance;
    private Date dateAchat;
    private String immatriculation;
    private float prixJournalier;
    private boolean actifVehicule;
    private List<Contient> contientsByIdVehicule;
    private List<Reservation> reservationsByIdVehicule;
    private Entrepot entrepotsByIdEntrepot;
    private Couleur couleursByIdCouleur;
    private Modele modelesByIdModele;

    public Vehicule() {

    }

    public Vehicule(String numChassis, int cylindree, int puissance, Date dateAchat, String immatriculation, float prixJournalier, Entrepot entrepot, Couleur couleur, Modele modele, Marque marque) {

        this.numChassis = numChassis;
        this.cylindree = cylindree;
        this.puissance = puissance;
        this.dateAchat = dateAchat;
        this.immatriculation = immatriculation;
        this.prixJournalier = prixJournalier;
        this.actifVehicule = true;
        this.entrepotsByIdEntrepot = entrepot;
        this.couleursByIdCouleur = couleur;
        this.modelesByIdModele = modele;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_vehicule", nullable = false)
    public int getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
    }

    @Basic
    @Column(name = "Num_chassis", nullable = false, length = 50)
    public String getNumChassis() {
        return numChassis;
    }

    public void setNumChassis(String numChassis) {
        this.numChassis = numChassis;
    }

    @Basic
    @Column(name = "Cylindree", nullable = false)
    public int getCylindree() {
        return cylindree;
    }

    public void setCylindree(int cylindree) {
        this.cylindree = cylindree;
    }

    @Basic
    @Column(name = "Puissance", nullable = false)
    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    @Basic
    @Column(name = "Date_achat", nullable = false)
    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    @Basic
    @Column(name = "Immatriculation", nullable = true, length = 50)
    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    @Basic
    @Column(name = "Prix_journalier", nullable = true, precision = 2)
    public float getPrixJournalier() {
        return prixJournalier;
    }

    public void setPrixJournalier(float prixJournalier) {
        this.prixJournalier = prixJournalier;
    }

    @Basic
    @Column(name = "Actif_vehicule", nullable = false)
    public boolean isActifVehicule() {
        return actifVehicule;
    }

    public void setActifVehicule(boolean actifVehicule) {
        this.actifVehicule = actifVehicule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicule vehicule = (Vehicule) o;
        return idVehicule == vehicule.idVehicule && cylindree == vehicule.cylindree && puissance == vehicule.puissance && actifVehicule == vehicule.actifVehicule && Objects.equals(numChassis, vehicule.numChassis) && Objects.equals(dateAchat, vehicule.dateAchat) && Objects.equals(immatriculation, vehicule.immatriculation) && Objects.equals(prixJournalier, vehicule.prixJournalier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVehicule, numChassis, cylindree, puissance, dateAchat, immatriculation, prixJournalier, actifVehicule);
    }

    @OneToMany(mappedBy = "vehiculesByIdVehicule")
    public List<Contient> getContientsByIdVehicule() {
        return contientsByIdVehicule;
    }

    public void setContientsByIdVehicule(List<Contient> contientsByIdVehicule) {
        this.contientsByIdVehicule = contientsByIdVehicule;
    }

    @OneToMany(mappedBy = "vehiculesByIdVehicule")
    public List<Reservation> getReservationsByIdVehicule() {
        return reservationsByIdVehicule;
    }

    public void setReservationsByIdVehicule(List<Reservation> reservationsByIdVehicule) {
        this.reservationsByIdVehicule = reservationsByIdVehicule;
    }

    @ManyToOne
    @JoinColumn(name = "Id_entrepot", referencedColumnName = "Id_entrepot", nullable = false)
    public Entrepot getEntrepotsByIdEntrepot() {
        return entrepotsByIdEntrepot;
    }

    public void setEntrepotsByIdEntrepot(Entrepot entrepotsByIdEntrepot) {
        this.entrepotsByIdEntrepot = entrepotsByIdEntrepot;
    }

    @ManyToOne
    @JoinColumn(name = "Id_couleur", referencedColumnName = "Id_couleur", nullable = false)
    public Couleur getCouleursByIdCouleur() {
        return couleursByIdCouleur;
    }

    public void setCouleursByIdCouleur(Couleur couleursByIdCouleur) {
        this.couleursByIdCouleur = couleursByIdCouleur;
    }

    @ManyToOne
    @JoinColumn(name = "Id_modele", referencedColumnName = "Id_modele", nullable = false)
    public Modele getModelesByIdModele() {
        return modelesByIdModele;
    }

    public void setModelesByIdModele(Modele modelesByIdModele) {
        this.modelesByIdModele = modelesByIdModele;
    }
}
