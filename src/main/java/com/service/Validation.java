package com.service;

import java.sql.Date;

/**
 * @author Vanconingsloo Kevin && Wets Jeoffroy
 */

public class Validation {


    public static String ucFirst (String mot){

        return mot.substring(0,1).toUpperCase() + mot.substring(1).toLowerCase();
    }

    public static Date dateFormat (String date){

        return Date.valueOf(date);
    }

    public static boolean checkValueIsEmpty(String value) {

        if (checkValueIsEmptyorNull(value)) {

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

    public static boolean checkValueIsIFloat(String value) {

        if (value.matches("([0-9]*[.])?[0-9]+")) {

            return true;
        }

        return false;
    }

    public static boolean checkValueIsEmptyorNull(String value) {

        if (value == null || value.equals("")) {

            return true;
        }

        return false;
    }

    public static boolean checkTelephoneFormat(String telephone) {

        if (telephone.matches("\\+?[0-9]+")) {

            return true;
        }

        return false;
    }

    public static boolean checkEmailFormat(String email) {

        if (email != null && email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)") && email.length() <= 100 ) {

            return true;
        }

        return false;
    }
}
