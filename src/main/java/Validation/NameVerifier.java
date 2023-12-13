package Validation;

public class NameVerifier {

    public static boolean verifyName(String name) throws Exception{

        name = name.trim();

        if(name == null || name.isEmpty()) {
            throw new Exception("The name cannot be empty");
        }else if(name.length() < 3) {
            throw new Exception("The name must be at least 3 characters");
        }else if(name.length() > 50) {
            throw new Exception("The name cannot be more than 50 characters");
        }else if(!name.matches("[a-zA-Z\\sáéíóúÁÉÍÓÚñÑ]+")){
            throw new Exception("The name can only contain letters");
        }else{
            return true;
        }

    }

}
