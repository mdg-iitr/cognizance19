package com.mdgiitr.karthik.cognizance19.utils;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailPasswordValidator {

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(final String password) {

        if (password.length() >= 8)
            return true;

        else return false;

    }

    public static boolean isPhoneValid(String phone){
        return Patterns.PHONE.matcher(phone).matches();
    }

}
