package Validation;

public class ContactNumberVerifier {

    public static boolean verifyContactNumber(String contactNumber) throws Exception{

        contactNumber = contactNumber.trim();

        if(contactNumber.length() < 9) {
            throw new Exception("The contact number must be at least 9 characters");
        }else if(contactNumber.length() > 9) {
            throw new Exception("The contact number cannot be more than 9 characters");
        }else if(!contactNumber.matches("[0-9]+")){
            throw new Exception("The contact number can only contain numbers");
        }else{
            return true;
        }

    }

}
