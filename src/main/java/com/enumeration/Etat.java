package com.enumeration;

/**
 * @author Jerôme Deschamps 
 */

public enum Etat {
    signe("signe"),
    paye("paye");

    private String etat;

    Etat(String etat) {
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }
}

