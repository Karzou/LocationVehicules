package com.service;

import java.sql.Date;

/**
 * @author Vanconingsloo Kevin
 */
public class Validation {

    public static boolean validationPrenom( String prenom ){
        if ( prenom != null && prenom.length() > 1 ) {
            return true;
        }
        return false;
    }

    public static boolean validationAdresse( String adresse ){
        if ( adresse != null && adresse.length() > 5) {
            return true;
        }
        return false;
    }

    public static boolean validationTelephone( String telephone ){
        if ( telephone != null  /*&& telephone.matches( "^\\d+$" ) &&  telephone.length() > 7 */) {
              return true;
        }
        return false;
    }
    public static boolean validationEmail( String email ){
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            return false;
        }
        return true;
    }

    public static boolean validationPassword (String password) {
        if (password.length() > 3 ){
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
}
