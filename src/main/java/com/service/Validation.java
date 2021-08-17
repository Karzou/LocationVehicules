package com.service;

import java.sql.Date;

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


    //------------------------------------------------------------------------------------------------------------------
    public static boolean checkValueIsEmpty(String value) {

        if (value.isEmpty()) {

            return true;
        }

        return false;
    }

    public static boolean checkValueLenght(String value, int min, int max) {

        if (value.length() < min || value.length() > max) {

            return false;
        }

        return true;
    }

    public static boolean checkValueLenghtMax(String value, int max) {

        if (value.length() > max) {

            return false;
        }

        return true;
    }

    public static boolean checkValueIsZero(String value) {

        if (value.matches("\\d+$")) {

            int result = Integer.parseInt(value);

            if (result == 0) {

                return true;
            }
        }

        return false;
    }

    public static boolean checkValueIsInteger(String value) {

        if (value.matches("\\d+$")) {

            return true;
        }

        return false;
    }

    public static boolean checkValueIsEmptyorNull(String value) {

        if (value == null || value == "") {

            return true;
        }

        return false;
    }
}
