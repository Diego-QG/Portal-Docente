import Entities.Teacher;
import Forms.Login;
import com.formdev.flatlaf.FlatLightLaf;

import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {

        FlatLightLaf.setup();
        Login login = new Login();

        Consumer<Teacher> onSuccess = loggedTeacher -> {
            System.out.println("Operación exitosa con el profesor: " + loggedTeacher.getFirstName() + " " + loggedTeacher.getLastName());
            loggedTeacher.printTeacherDetails();
        };

        login.setOnSuccess(onSuccess);

    }

}
