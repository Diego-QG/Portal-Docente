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
    private JTextField jtfFirstname;
    private JPanel jplFirstname;
    private JLabel jlFirsname;
    private JPanel jpLastname;
    private JPanel jplLastname;
    private JLabel jlLastname;
    private JPanel jptfLastname;
    private JTextField jtfLastname;
    private JPanel jptfFirstname;
    private JPanel jpGender;
    private JPanel jplGender;
    private JLabel jlGender;
    private JPanel jpoGenders;
    private JRadioButton jrbtnMale;
    private JRadioButton jrbtnFemale;
    private JRadioButton jrbtnNonBinary;
    private JPanel jpDay;
    private JComboBox jcboxDay;
    private JLabel jlBirthday;
    private JLabel jlContactNumber;
    private JLabel jlSalary;
    private JLabel jlJobTitle;
    private JLabel jlYearsOfExperience;
    private JButton jbtnAcceptChanges;
    private JPanel jpBirthday;
    private JPanel jpContactNumber;
    private JPanel jpJobTitle;
    private JPanel jpSalary;
    private JPanel jpYearsOfExperience;
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

        jpFirstname.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        jlFirsname.putClientProperty("FlatLaf.style", "font: 16 $light.font");
        jlFirsname.setForeground(Color.BLACK);

        jpLastname.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        jlLastname.putClientProperty("FlatLaf.style", "font: 16 $light.font");
        jlLastname.setForeground(Color.BLACK);

        jpGender.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        jlGender.putClientProperty("FlatLaf.style", "font: 16 $light.font");
        jlGender.setForeground(Color.BLACK);

        jpBirthday.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        jlBirthday.putClientProperty("FlatLaf.style", "font: 16 $light.font");
        jlBirthday.setForeground(Color.BLACK);

        jpContactNumber.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        jlContactNumber.putClientProperty("FlatLaf.style", "font: 16 $light.font");
        jlContactNumber.setForeground(Color.BLACK);

        jpJobTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        jlJobTitle.putClientProperty("FlatLaf.style", "font: 16 $light.font");
        jlJobTitle.setForeground(Color.BLACK);

        jpSalary.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        jlSalary.putClientProperty("FlatLaf.style", "font: 16 $light.font");
        jlSalary.setForeground(Color.BLACK);

        jpYearsOfExperience.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        jlYearsOfExperience.putClientProperty("FlatLaf.style", "font: 16 $light.font");
        jlYearsOfExperience.setForeground(Color.BLACK);



        //jlTodayIs.setText("Today is " + today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + String.valueOf(today.getDayOfMonth()) + " " + today.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + String.valueOf(today.getYear()));
        //jlTodayIs.putClientProperty( "FlatLaf.style", "font: 24 italic $light.font" );
        //jlTodayIs.setForeground(Color.WHITE);
    }

}
