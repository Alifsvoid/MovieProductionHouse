package com.spring26.section2.group13.movieproductionhouse.akkhorik.util;


import java.time.LocalDate;

public class ValidationUtil {

    public static boolean isValidLicensePeriod(LocalDate start, LocalDate end) {
        return start != null && end != null && end.isAfter(start);
    }

    public static boolean isValidSerial(String serial) {
        return serial != null && serial.matches("^[A-Z0-9]{8,20}$");
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean isPositiveNumber(String str) {
        try {
            double d = Double.parseDouble(str);
            return d > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isPositiveInteger(String str) {
        try {
            int i = Integer.parseInt(str);
            return i > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
