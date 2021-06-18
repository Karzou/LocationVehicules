package com.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */
 
@Entity
@NamedQueries({
        @NamedQuery(name = "Contient.lister", query = "SELECT c from Contient c"),
})
public class Contient {
    private int idContient;
    private OptionVehicule optionsVehiculesByIdOption;
    private Vehicule vehiculesByIdVehicule;

    public Contient() {

    }

    public Contient(OptionVehicule optionVehicule, Vehicule vehicule) {

        this.optionsVehiculesByIdOption = optionVehicule;
        this.vehiculesByIdVehicule = vehicule;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_contient", nullable = false)
    public int getIdContient() {
        return idContient;
    }

    public void setIdContient(int idContient) {
        this.idContient = idContient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contient contient = (Contient) o;
        return idContient == contient.idContient;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContient);
    }

    @ManyToOne
    @JoinColumn(name = "Id_option", referencedColumnName = "Id_option", nullable = false)
    public OptionVehicule getOptionsVehiculesByIdOption() {
        return optionsVehiculesByIdOption;
    }

    public void setOptionsVehiculesByIdOption(OptionVehicule optionsVehiculesByIdOption) {
        this.optionsVehiculesByIdOption = optionsVehiculesByIdOption;
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
