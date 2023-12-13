package Validation;

public class EmailVerifier {

    public static boolean verifyEmail(String email) throws Exception{

        email = formatEmail(email);

        if(email == null || email.isEmpty()) {
            throw new Exception("The email cannot be empty");
        }else if(!email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")){
            throw new Exception("The email is not valid");
        }else{
            return true;
        }

    }

    public static String formatEmail(String email) {
        return email.trim().toLowerCase();
    }

}
