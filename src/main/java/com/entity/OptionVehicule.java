package com.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */
@Entity
@Table(name = "options_vehicules", schema = "location_vehicules")
public class OptionVehicule {
    private int idOption;
    private String nomOption;
    private List<Contient> contientsByIdOption;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_option", nullable = false)
    public int getIdOption() {
        return idOption;
    }

    public void setIdOption(int idOption) {
        this.idOption = idOption;
    }

    @Basic
    @Column(name = "Nom_option", nullable = false, length = 100)
    public String getNomOption() {
        return nomOption;
    }

    public void setNomOption(String nomOption) {
        this.nomOption = nomOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionVehicule that = (OptionVehicule) o;
        return idOption == that.idOption && Objects.equals(nomOption, that.nomOption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOption, nomOption);
    }

    @OneToMany(mappedBy = "optionsVehiculesByIdOption")
    public List<Contient> getContientsByIdOption() {
        return contientsByIdOption;
    }

    public void setContientsByIdOption(List<Contient> contientsByIdOption) {
        this.contientsByIdOption = contientsByIdOption;
    }
}
