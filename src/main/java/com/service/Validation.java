package com.service;

/**
 * @author Kevin Vanconingsloo
 */
public class Validation {

    public void validationNom( String nom ) throws Exception {
        if ( nom != null ) {
            if ( nom.length() < 2 ) {
                throw new Exception( "Le nom d'utilisateur doit contenir au moins 2 caractères." );
            }
        } else {
            throw new Exception( "Merci d'entrer un nom d'utilisateur." );
        }
    }

    public boolean validationPrenom( String prenom ) throws Exception {
        if ( prenom != null && prenom.length() > 2 ) {
            return true;
        }
        return false;
    }

    public boolean validationAdresse( String adresse ) throws Exception {
        if ( adresse != null && adresse.length() > 10) {
            return true;
        }
        return false;
    }

    public boolean validationTelephone( String telephone ) throws Exception {
        if ( telephone != null && telephone.matches( "^\\d+$" ) &&  telephone.length() > 4 ) {
              return true;
        }
        return false;
    }
    private void validationEmail( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }
}
