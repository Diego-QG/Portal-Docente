package Forms.Views;

import Entities.Teacher;

import javax.swing.*;

public class ViewCourses extends JPanel{
    private JPanel jpViewCourses;
    private JPanel jpBackground;
    private Teacher loggedTeacher = new Teacher();


    public ViewCourses(Teacher loggedTeacher) {
        this.loggedTeacher = loggedTeacher;
        add(jpViewCourses);
        //setSize(737, 660);
        setSize(750, 670);
        setLocation(0, 0);
        //initStyles();
    }
}
