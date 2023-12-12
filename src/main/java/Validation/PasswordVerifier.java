package Validation;

public class PasswordVerifier {

    public static boolean verifyPassword(char[] passwordChars) throws Exception{

        String password = new String(passwordChars);

        if (password.isEmpty()) {
            throw new Exception("The password cannot be empty");
        } else if (password.length() < 8) {
            throw new Exception("The password must be at least 8 characters");
        } else if (password.length() > 50) {
            throw new Exception("The password cannot be more than 50 characters");
        } else if (!password.matches(".*[0-9].*")) {
            throw new Exception("The password must contain at least one number");
        } else if (!password.matches(".*[a-z].*")) {
            throw new Exception("The password must contain at least one lowercase letter");
        } else if (!password.matches(".*[A-Z].*")) {
            throw new Exception("The password must contain at least one uppercase letter");
        } else {
            return true;
        }

    }

    public static String formatPassword(char[] passwordChars) {
        return new String(passwordChars);
    }

}
