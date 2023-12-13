package Forms.Views;

import Entities.Teacher;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class ViewPrincipal extends JPanel{
    private JPanel jpViewPrincipal;
    private JLabel jlWelcomeTeacher;
    private JPanel jpBackground;
    private JPanel jpWelcome;
    private JPanel jpHeader;
    private JLabel jlWelcomePortal;
    private JLabel jlTodayIs;
    private JPanel jpSubPrincipal;
    private Teacher loggedTeacher = new Teacher();
    private LocalDate today = LocalDate.now();

    public ViewPrincipal(Teacher loggedTeacher) {
        this.loggedTeacher = loggedTeacher;
        add(jpViewPrincipal);
        //setSize(737, 660);
        setSize(750, 670);
        setLocation(0, 0);
        initStyles();
    }

    public void initStyles() {
        jlWelcomeTeacher.setText("Welcome teacher, " + loggedTeacher.getFirstName() + " " + loggedTeacher.getLastName() + " nice to see you!");
        jlWelcomeTeacher.putClientProperty("FlatLaf.style", "font: 16 $light.font");
        jlWelcomeTeacher.setForeground(Color.BLACK);

        jlWelcomePortal.putClientProperty( "FlatLaf.style", "font: 20 bold $light.font" );
        jlWelcomePortal.setForeground(Color.WHITE);

        jlTodayIs.setText("Today is " + today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + String.valueOf(today.getDayOfMonth()) + " " + today.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " + String.valueOf(today.getYear()));
        jlTodayIs.putClientProperty( "FlatLaf.style", "font: 24 italic $light.font" );
        jlTodayIs.setForeground(Color.WHITE);
    }


}
