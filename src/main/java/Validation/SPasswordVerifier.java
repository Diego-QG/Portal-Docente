package Validation;

public class SPasswordVerifier {

    public static boolean verifySPassword(char[] passwordChars, char[] sPasswordChars) throws Exception{

        String password = new String(passwordChars);
        String sPassword = new String(sPasswordChars);

        if (sPassword.isEmpty()) {
            throw new Exception("The second password cannot be empty");
        } else if (!sPassword.equals(password)) {
            throw new Exception("The passwords do not match");
        } else {
            return true;
        }

    }

}
