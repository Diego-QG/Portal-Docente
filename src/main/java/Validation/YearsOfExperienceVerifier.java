package Validation;

public class YearsOfExperienceVerifier {

    public static boolean verifyYearsOfExperience(String yearsOfExperience) throws Exception{

        yearsOfExperience = yearsOfExperience.trim();

        if(!yearsOfExperience.matches("[0-9]+")){
            throw new Exception("The years of experience can only contain numbers");
        }else if(Integer.parseInt(yearsOfExperience) < 0) {
            throw new Exception("The years of experience cannot be negative");
        }else{
            return true;
        }

    }

}
