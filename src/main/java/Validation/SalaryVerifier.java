package Validation;

public class SalaryVerifier {

    public static boolean verifySalary(String salary) throws Exception {

        salary = salary.trim();

        try {
            float salaryValue = Float.parseFloat(salary);

            if (salaryValue < 0) {
                throw new Exception("The salary cannot be negative");
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            throw new Exception("The salary must be a valid number");
        }
    }

}
