package com.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */
 
@Entity
public class Choisir {
    private int idChoisir;
    private OptionSupplementaire optionsSupplementairesByIdOptionSup;
    private Reservation reservationsByIdReservation;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_choisir", nullable = false)
    public int getIdChoisir() {
        return idChoisir;
    }

    public void setIdChoisir(int idChoisir) {
        this.idChoisir = idChoisir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Choisir choisir = (Choisir) o;
        return idChoisir == choisir.idChoisir;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idChoisir);
    }

    @ManyToOne
    @JoinColumn(name = "Id_option_sup", referencedColumnName = "Id_option_sup", nullable = false)
    public OptionSupplementaire getOptionsSupplementairesByIdOptionSup() {
        return optionsSupplementairesByIdOptionSup;
    }

    public void setOptionsSupplementairesByIdOptionSup(OptionSupplementaire optionsSupplementairesByIdOptionSup) {
        this.optionsSupplementairesByIdOptionSup = optionsSupplementairesByIdOptionSup;
    }

    @ManyToOne
    @JoinColumn(name = "Id_reservation", referencedColumnName = "Id_reservation", nullable = false)
    public Reservation getReservationsByIdReservation() {
        return reservationsByIdReservation;
    }

    public void setReservationsByIdReservation(Reservation reservationsByIdReservation) {
        this.reservationsByIdReservation = reservationsByIdReservation;
    }
}
