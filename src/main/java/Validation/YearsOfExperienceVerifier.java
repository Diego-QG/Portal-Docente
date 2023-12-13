package Validation;

public class YearsOfExperienceVerifier {

    public static boolean verifyYearsOfExperience(String yearsOfExperience) throws Exception{

        yearsOfExperience = yearsOfExperience.trim();

        if (yearsOfExperience == null || yearsOfExperience.isEmpty()) {
            return true;
        }

        try {
            int yearsExpValue = Integer.parseInt(yearsOfExperience);

            if (yearsExpValue < 0) {
                throw new Exception("The years of experience cannot be negative");
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            throw new Exception("The years of experience must be a valid number");
        }

    }

}
