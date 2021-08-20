package com.entity;

import com.enumeration.Etat;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */

@Entity
@Table(name = "contrats", schema = "location_vehicules")
@NamedQueries({
        @NamedQuery(name = "Contrat.getAll", query = "SELECT c FROM Contrat c")})

public class Contrat {
    private int idContrat;
    private float acompte;
    private float caution;
    private Etat etat;
    private List<Facture> facturesByIdContrat;
    private List<Reservation> reservationsByIdContrat;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_contrat", nullable = false)
    public int getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(int idContrat) {
        this.idContrat = idContrat;
    }

    @Basic
    @Column(name = "Acompte", nullable = true, precision = 2)
    public float getAcompte() {
        return acompte;
    }

    public void setAcompte(float acompte) {
        this.acompte = acompte;
    }

    @Basic
    @Column(name = "Caution", nullable = true, precision = 2)
    public float getCaution() {
        return caution;
    }

    public void setCaution(float caution) {
        this.caution = caution;
    }

    @Enumerated(EnumType.STRING)
    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrat contrat = (Contrat) o;
        return idContrat == contrat.idContrat &&
                Float.compare(contrat.acompte, acompte) == 0 &&
                Float.compare(contrat.caution, caution) == 0 &&
                etat == contrat.etat &&
                Objects.equals(facturesByIdContrat, contrat.facturesByIdContrat) &&
                Objects.equals(reservationsByIdContrat, contrat.reservationsByIdContrat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContrat, acompte, caution, etat, facturesByIdContrat, reservationsByIdContrat);
    }

    @OneToMany(mappedBy = "contratsByIdContrat")
    public List<Facture> getFacturesByIdContrat() {
        return facturesByIdContrat;
    }

    public void setFacturesByIdContrat(List<Facture> facturesByIdContrat) {
        this.facturesByIdContrat = facturesByIdContrat;
    }

    @OneToMany(mappedBy = "contratsByIdContrat")
    public List<Reservation> getReservationsByIdContrat() {
        return reservationsByIdContrat;
    }

    public void setReservationsByIdContrat(List<Reservation> reservationsByIdContrat) {
        this.reservationsByIdContrat = reservationsByIdContrat;
    }
}
