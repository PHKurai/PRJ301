/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author phucl
 */
public class ValidUtils {
    
    public static boolean isValidStatus(String status) {
        boolean result = false;
        String[] validStatus = {"Ideation", "Development", "Launch", "Scaling"};

        for (int i = 0; i < validStatus.length; i++) {
            if (validStatus[i].equalsIgnoreCase(status)) {
                result = true;
                break;
            }
        }

        return result;
    }
    
    public static boolean isValidDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
