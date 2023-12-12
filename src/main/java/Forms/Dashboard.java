package Forms;

import Entities.Teacher;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Dashboard extends JFrame {

    private JPanel jpDashboard;
    private JPanel jpBackground;
    private JButton button1;
    private JTextField textField1;
    private Teacher loggedTeacher;

    public Dashboard(Teacher teacher) {
        super("Dashboard");
        setContentPane(jpDashboard);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        //setUndecorated(true);
        //Shape shape = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 25, 25);
        //this.setShape(shape);
        loggedTeacher = teacher;



        setVisible(true);
    }

}
