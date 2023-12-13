package Forms.Views;

import Entities.Teacher;

import javax.swing.*;
import java.awt.*;

public class ViewProfile extends JPanel {
    private JPanel jpViewProfile;
    private JPanel jpBackground;
    private JPanel jpHeader;
    private JLabel jlEditProfile;
    private JPanel jpForm;
    private JPanel jpFirstname;
    private JTextField textField1;
    private JPanel jpLFirstname;
    private JLabel jlFirsname;
    private Teacher loggedTeacher = new Teacher();


    public ViewProfile(Teacher loggedTeacher) {
        this.loggedTeacher = loggedTeacher;
        add(jpViewProfile);
        setSize(750, 670);
        setLocation(0, 0);
        initStyles();
    }

    public void initStyles() {
        jlEditProfile.putClientProperty( "FlatLaf.style", "font: 20 bold $light.font" );
        jlEditProfile.setForeground(Color.WHITE);

        jlFirsname.putClientProperty("FlatLaf.style", "font: 16 $light.font");
        jlFirsname.setForeground(Color.BLACK);

        //jlTodayIs.setText("Today is " + today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + String.valueOf(today.getDayOfMonth()) + " " + today.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + String.valueOf(today.getYear()));
        //jlTodayIs.putClientProperty( "FlatLaf.style", "font: 24 italic $light.font" );
        //jlTodayIs.setForeground(Color.WHITE);
    }

}
