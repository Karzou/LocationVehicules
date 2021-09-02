package com.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */
 
@Entity
@Table(name = "paiements", schema = "location_vehicules")
public class Paiement {
    private int idPaiement;
    private String typePaiement;
    private Boolean actifPaiement;
    private List<Reservation> reservationsByIdPaiement;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_paiement", nullable = false)
    public int getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(int idPaiement) {
        this.idPaiement = idPaiement;
    }

    @Basic
    @Column(name = "Type_paiement", nullable = false, length = 50)
    public String getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }

    @Basic
    @Column(name = "Actif_paiement", nullable = true)
    public Boolean getActifPaiement() {
        return actifPaiement;
    }

    public void setActifPaiement(Boolean actifPaiement) {
        this.actifPaiement = actifPaiement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paiement paiement = (Paiement) o;
        return idPaiement == paiement.idPaiement && Objects.equals(typePaiement, paiement.typePaiement) && Objects.equals(actifPaiement, paiement.actifPaiement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPaiement, typePaiement, actifPaiement);
    }

    @OneToMany(mappedBy = "paiementsByIdPaiement")
    public List<Reservation> getReservationsByIdPaiement() {
        return reservationsByIdPaiement;
    }

    public void setReservationsByIdPaiement(List<Reservation> reservationsByIdPaiement) {
        this.reservationsByIdPaiement = reservationsByIdPaiement;
    }
}
