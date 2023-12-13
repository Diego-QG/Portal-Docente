package Validation;

public class ContactNumberVerifier {

    public static boolean verifyContactNumber(String contactNumber) throws Exception{

        contactNumber = contactNumber.trim();

        if (contactNumber == null || contactNumber.isEmpty()) {
            return true;
        }

        try {
            int numberValue = Integer.parseInt(contactNumber);

            if (numberValue < 0) {
                throw new Exception("The contact number cannot be negative");
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            throw new Exception("The contact number must be a valid number");
        }

    }

}
