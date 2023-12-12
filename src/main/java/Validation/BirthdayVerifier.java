package Validation;

import java.time.LocalDate;

public class BirthdayVerifier {

    public static boolean verifyBirthday(Object day, Object month, Object year) throws Exception{

        String dateError = "";

        if(day == null || day.toString().isEmpty()) {
            dateError += "\n    Select a day";
        }
        if(month == null || month.toString().isEmpty()) {
            dateError += "\n    Select a month";
        }
        if(year == null || year.toString().isEmpty()) {
            dateError += "\n    Select a year";
        }
        if (!dateError.isEmpty()) {
            throw new Exception("Date errors:" + dateError);
        }

        return true;
    }

    public static LocalDate formatBirthday(Object day, Object month, Object year) {
        int dayInt = Integer.parseInt(day.toString());
        int monthInt = Integer.parseInt(month.toString());
        int yearInt = Integer.parseInt(year.toString());
        return LocalDate.of(yearInt, monthInt, dayInt);
    }

}
