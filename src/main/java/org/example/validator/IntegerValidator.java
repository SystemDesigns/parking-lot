package org.example.validator;

public class IntegerValidator {

    public static boolean isInteger(String stringValue) {
        try {
            Integer.parseInt(stringValue);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
