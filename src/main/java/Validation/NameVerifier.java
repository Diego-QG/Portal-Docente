package Validation;

public class NameVerifier {

    public static boolean verifyName(String name) throws Exception{

        if(name == null || name.isEmpty()) {
            throw new Exception("The name cannot be empty");
        }else if(name.length() < 3) {
            throw new Exception("The name must be at least 3 characters");
        }else if(name.length() > 50) {
            throw new Exception("The name cannot be more than 50 characters");
        }else if(!name.matches("[a-zA-Z]+")){
            throw new Exception("The name can only contain letters");
        }else{
            return true;
        }

    }

    public static String formatName(String name) {
        String formattedName = name.trim().toLowerCase();
        formattedName = capitalize(formattedName);
        return formattedName;
    }

    public static String capitalize(String inputString) {
        char firstLetter = inputString.charAt(0);
        char capitalFirstLetter = Character.toUpperCase(firstLetter);
        return inputString.replace(inputString.charAt(0), capitalFirstLetter);
    }

}
