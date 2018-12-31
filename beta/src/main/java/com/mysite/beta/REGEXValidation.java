package com.mysite.beta;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class REGEXValidation {

    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final int MINSTRLEN = 3;
    private final int MAXSTRLEN = 255;

    public boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public boolean validateString(String Str) {
       if((Str instanceof String) && Str.length() >= MINSTRLEN && Str.length() <= MAXSTRLEN){
           return true;
       }
       return false;
    }
}
