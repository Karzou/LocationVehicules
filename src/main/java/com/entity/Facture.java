package com.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * @authors Jérôme Deschamps
 */
 
@Entity
@Table(name = "factures", schema = "location_vehicules")
@NamedQueries({
        @NamedQuery(name = "Facture.getAll", query = "SELECT f FROM Facture f")
})
public class Facture {
    private int idFacture;
    private Date dateFacture;
    private float prixFacture;
    private Contrat contratsByIdContrat;
    private Vehicule vehicule ;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_facture", nullable = false)
    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    @Basic
    @Column(name = "Date_facture", nullable = false)
    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    @Basic
    @Column(name = "Prix_facture", nullable = false, precision = 2)
    public float getPrixFacture() {
        return prixFacture;
    }

    public void setPrixFacture(float prixFacture) {
        this.prixFacture = prixFacture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facture facture = (Facture) o;
        return idFacture == facture.idFacture && Objects.equals(dateFacture, facture.dateFacture) && Objects.equals(prixFacture, facture.prixFacture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFacture, dateFacture, prixFacture);
    }

    @ManyToOne
    @JoinColumn(name = "Id_contrat", referencedColumnName = "Id_contrat", nullable = false)
    public Contrat getContratsByIdContrat() {
        return contratsByIdContrat;
    }

    public void setContratsByIdContrat(Contrat contratsByIdContrat) {
        this.contratsByIdContrat = contratsByIdContrat;
    }
}
