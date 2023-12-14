package Forms;

import Entities.Teacher;
import Forms.Views.*;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Dashboard extends JFrame {

    private JPanel jpDashboard;
    private JPanel jpMenu;
    private JPanel jpContent;
    private JLabel jlWelcomeTeacher;
    private JLabel jlWelcomePortal;
    private JLabel jlTodayIs;
    private JPanel jpMenuHeader;
    private JLabel jlOptions;
    private JLabel jlSeparator;
    private JButton jbtnPrincipal;
    private JPanel jMenuItems;
    private JPanel jMenuFooter;
    private JButton jbtnProfile;
    private JButton jbtnCourses;
    private JButton jbtnGroups;
    private JPanel jpHeader;
    private JLabel jlX;
    private JButton jbtnStudents;
    private JButton jbtnSignOut;
    private Teacher loggedTeacher = new Teacher();
    private LocalDate today = LocalDate.now();
    private int xMouse, yMouse;

    public Dashboard(Teacher loggedTeacher) {
        super("Portal del Docente");
        setContentPane(jpDashboard);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setUndecorated(true);
        Shape shape = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 25, 25);
        this.setShape(shape);
        setIconImage(new ImageIcon("src/main/resources/image.signupx128.png").getImage());

        this.loggedTeacher = loggedTeacher;
        initStyles();

        addMouseListenerToHeader();
        addMouseMotionListenerToHeader();
        addMouseListenerToCloseButton();

        showJPanel(new ViewPrincipal(loggedTeacher));

        clickPrincipal(jbtnPrincipal, loggedTeacher);
        clickProfile(jbtnProfile, loggedTeacher);
        clickCourses(jbtnCourses, loggedTeacher);
        clickGroups(jbtnGroups, loggedTeacher);
        clickStudents(jbtnStudents, loggedTeacher);

        jbtnSignOut.addActionListener(evt -> {
            new Login();
            dispose();
        });


        setVisible(true);

    }

    public void initStyles() {

        jlOptions.putClientProperty( "FlatLaf.style", "font: 20 bold $h3.regular.font" );
        jlOptions.setForeground(Color.WHITE);
        jlSeparator.putClientProperty( "FlatLaf.style", "font: 20 bold $h3.regular.font" );
        jlSeparator.setForeground(Color.WHITE);

        applyCursorEffect(jbtnPrincipal);
        applyCursorEffect(jbtnProfile);
        applyCursorEffect(jbtnCourses);
        applyCursorEffect(jbtnGroups);
        applyCursorEffect(jbtnStudents);
        applyCursorEffect(jbtnSignOut);


    }

    private void applyCursorEffect(JButton button) {
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setBackground(Color.decode("#a36351"));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void showJPanel(JPanel panel) {
        jpContent.removeAll();
        jpContent.add(panel);
        jpContent.repaint();
        jpContent.revalidate();
    }

    private void clickPrincipal(JButton boton, Teacher teacher) {
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showJPanel(new ViewPrincipal(teacher));
            }
        });
    }

    private void clickProfile(JButton boton, Teacher teacher) {
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showJPanel(new ViewProfile(teacher));
            }
        });
    }

    private void clickCourses(JButton boton, Teacher teacher) {
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showJPanel(new ViewCourses(teacher));
            }
        });
    }

    private void clickGroups(JButton boton, Teacher teacher) {
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showJPanel(new ViewGroups(teacher));
            }
        });
    }

    private void clickStudents(JButton boton, Teacher teacher) {
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showJPanel(new ViewStudents(teacher));
            }
        });
    }

    private void addMouseListenerToHeader() {
        jpHeader.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });
    }

    private void addMouseMotionListenerToHeader() {
        jpHeader.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - xMouse, y - yMouse);
            }
        });
    }

    private void addMouseListenerToCloseButton() {
        jlX.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                jlX.setOpaque(true);
                jlX.setBackground(new Color(222, 33, 39));
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                jlX.setBackground(null);
                jlX.setOpaque(false);
                super.mouseExited(e);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                super.mouseClicked(e);
            }
        });
    }

    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.setTeacherID("10");
        teacher.setFirstName("Juan");
        teacher.setLastName("Perez");
        teacher.setGender("Male");
        teacher.setBirthday(LocalDate.of(1990, 1, 24));
        teacher.setContactNumber("1234567890");
        teacher.setJobTitle("Teacher");
        teacher.setSalary(1000);
        teacher.setYearsOfExperience(10);
        FlatLightLaf.setup();

        new Dashboard(teacher);
    }

}
