package Validation;

public class JobTitleVerifier {

    public static boolean verifyJobTitle(String jobTitle) throws Exception{

        jobTitle = jobTitle.trim();
        if (jobTitle == null || jobTitle.isEmpty()) {
            return true;
        }else if(jobTitle.length() > 50) {
            throw new Exception("The job title cannot be more than 50 characters");
        }else if(!jobTitle.matches("[a-zA-Z\\sáéíóúÁÉÍÓÚñÑ]+")){
            throw new Exception("The job title can only contain letters");
        }else{
            return true;
        }

    }

}
