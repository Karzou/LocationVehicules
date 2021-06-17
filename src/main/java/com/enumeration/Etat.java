package com.enumeration;

/**
 * @author Wets Jeoffroy
 */

public enum Etat {

    SIGNE("Signé"),
    PAYE("Payé");

    private String etat;

    private Etat(String etat) {
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }
}

