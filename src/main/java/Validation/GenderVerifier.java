package Validation;

public class GenderVerifier {

    public static boolean verifyGender(String gender) throws Exception{

        if(gender == null || gender.isEmpty()) {
            throw new Exception("Select one of the options");
        } else {
            return true;
        }

    }

    public static String formatGender(String gender) {
        return gender;
    }

}
