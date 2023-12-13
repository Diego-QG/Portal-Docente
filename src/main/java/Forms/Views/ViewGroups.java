package Forms.Views;

import Entities.Teacher;

import javax.swing.*;

public class ViewGroups extends JPanel{
    private JPanel jpViewGroups;
    private JPanel jpBackground;
    private Teacher loggedTeacher = new Teacher();


    public ViewGroups(Teacher loggedTeacher) {
        this.loggedTeacher = loggedTeacher;
        add(jpViewGroups);
        //setSize(737, 660);
        setSize(750, 670);
        setLocation(0, 0);
        //initStyles();
    }
}
