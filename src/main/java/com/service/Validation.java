package com.service;

import java.sql.Date;
import java.sql.Time;

/**
 * @author Vanconingsloo Kevin
 */
public class Validation {

    public static boolean validationPrenom( String prenom ){
        if ( prenom != null && prenom.length() > 1 && prenom.length() < 51) {

            return true;
        }
        return false;
    }

    public static boolean validationAdresse( String adresse ){
        if ( adresse != null && adresse.length() > 5 && adresse.length() < 101) {
            return true;
        }
        return false;
    }

    public static boolean validationNumAdresse (String num){
        if( num != null && num.length() < 11){
            return true;
        }
        return false;
    }

    public static boolean validationTelephone( String telephone ){
        if ( telephone != null  && telephone.matches( "^\\d+$" ) &&  telephone.length() > 7  && telephone.length() < 51) {
              return true;
        }
        return false;
    }
    public static boolean validationEmail( String email ){
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) && email.length() < 101 ) {
            return false;
        }
        return true;
    }

    public static boolean validationPassword (String password) {
        if (password.length() > 3 && password.length() < 256){
            return true;
        }
        return false;
    }

    public static boolean validationNomPermRole (String nomPermRole){
        if (nomPermRole != null && nomPermRole.length() < 51){
            return true;
        }
        return false;
    }

    public static String ucFirst (String mot){

        return mot.substring(0,1).toUpperCase() + mot.substring(1).toLowerCase();
    }

    public static String upperCase (String mot) {

        return mot.toUpperCase();
    }

    public static Date dateFormat (String date){

        return Date.valueOf(date);
    }

    public static boolean checkColorIsEmpty(String couleur) {

        if (couleur.isEmpty()) {

            return true;
        }

        return false;
    }

    public static boolean checkColorLenght(String couleur) {

        if (couleur.length() < 2 || couleur.length() > 50) {

            return false;
        }

        return true;
    }

    public static boolean checkNomEntrepotIsEmpty(String nomEntrepot) {

        if (nomEntrepot.isEmpty()) {

            return true;
        }

        return false;
    }

    public static boolean checkNomEntrepotLenght(String nomEntrepot) {

        if (nomEntrepot.length() < 2 || nomEntrepot.length() > 50) {

            return false;
        }

        return true;
    }

    public static boolean checkNombrePlaceEntrepotIsEmpty(String nombrePlaceEntrepot) {

        if (nombrePlaceEntrepot.isEmpty()) {

            return true;
        }

        return false;
    }

    public static boolean checkNombrePlaceEntrepotIsZero(String nombrePlaceEntrepot) {

        if (nombrePlaceEntrepot.matches("\\d+$")) {

            int result = Integer.parseInt(nombrePlaceEntrepot);

            if (result == 0) {

                return true;
            }
        }

        return false;
    }

    public static boolean checkNombrePlaceEntrepotIsNumeric(String nombrePlaceEntrepot) {

        if (nombrePlaceEntrepot.matches("\\d+$")) {

            return true;
        }

        return false;
    }

    public static boolean checkNombrePlaceEntrepotLenght(String nombrePlaceEntrepot) {

        if (nombrePlaceEntrepot.length() > 10) {

            return false;
        }

        return true;
    }

    public static boolean checkRueEntrepotIsEmpty(String rueEntrepot) {

        if (rueEntrepot.isEmpty()) {

            return true;
        }

        return false;
    }

    public static boolean checkRueEntrepotLenght(String rueEntrepot) {

        if (rueEntrepot.length() < 2 || rueEntrepot.length() > 100) {

            return false;
        }

        return true;
    }

    public static boolean checkNumeroEntrepotIsEmpty(String numeroEntrepot) {

        if (numeroEntrepot.isEmpty()) {

            return true;
        }

        return false;
    }

    public static boolean checkNumeroEntrepotLenght(String numeroEntrepot) {

        if (numeroEntrepot.length() > 10) {

            return false;
        }

        return true;
    }

    public static boolean checkBoiteEntrepotLenght(String boiteEntrepot) {

        if (boiteEntrepot.length() > 10) {

            return false;
        }

        return true;
    }
}
