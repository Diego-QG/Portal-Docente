package Forms;

import Entities.Teacher;
import Entities.Subject;
import Connection.UConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Login extends JFrame{
    private JPanel jpLogin02;
    private JPanel jpHeader;
    private JPanel jpPrincipal;
    private JLabel jlX;
    private JPanel jpLogo;
    private JPanel jpFormLogin;
    private JPanel jpEmail;
    private JPanel jpPassword;
    private JPanel jpToSignUp;
    private JTextField jtfEmail;
    private JPasswordField jpfPassword;
    private JPanel jbBtnLogin;
    private JButton btnLogin;
    private JButton btnToSignUp;
    private JLabel jlLogoLogin;
    public int xMouse, yMouse;
    private Teacher loggedTeacher;
    private Consumer<Teacher> onSuccess;

    public Teacher getAuthenticatedTeacher() {
        return loggedTeacher;
    }

    public Login() {
        super("Login");
        setContentPane(jpLogin02);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setLocationRelativeTo(null);
        setUndecorated(true);
        Shape shape = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 25, 25);
        this.setShape(shape);
        setIconImage(new ImageIcon("src/main/resources/image.loginx128.png").getImage());

        SetImageLabel(jlLogoLogin, "src/main/resources/image.loginx128.png", 64, 64);


        jpHeader.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });

        jpHeader.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();

                setLocation(x - xMouse, y - yMouse);
            }
        });
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

        btnToSignUp.addActionListener(evt -> {
            new SignUp();
            dispose();
        });

        btnLogin.addActionListener(evt -> authenticateUser());

        setVisible(true);

    }

    public void setOnSuccess(Consumer<Teacher> onSuccess) {
        this.onSuccess = onSuccess;
    }

    private void SetImageLabel(JLabel label, String path, int width, int height) {
        ImageIcon image = new ImageIcon(path);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        label.setIcon(icon);
        this.repaint();
    }

    private void authenticateUser() {
        String email = jtfEmail.getText();
        String password = String.valueOf(jpfPassword.getPassword());

        loggedTeacher  = getAuthenticatedUser(email, password);

        if (loggedTeacher  != null) {
            if (onSuccess != null) {
                onSuccess.accept(loggedTeacher);
            }
            JOptionPane.showMessageDialog(Login.this, "Autenticación exitosa.");
            new Dashboard(loggedTeacher);
            dispose();
        } else {
            JOptionPane.showMessageDialog(Login.this,
                    "Usuario o contraseña incorrectos",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private Teacher getAuthenticatedUser(String email, String password) {
        Teacher teacher = null;

        try (Connection conn = UConnection.getConnection()) {

            Statement stmt = conn.createStatement();
            String sql = "select * from Teacher where email = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                teacher = new Teacher();
                teacher.setTeacherID(rs.getString("teacher_id"));
                teacher.setEmail(rs.getString("email"));
                teacher.setPassword(rs.getString("password"));
                teacher.setFirstName(rs.getString("firstName"));
                teacher.setLastName(rs.getString("lastName"));
                teacher.setGender(rs.getString("gender"));
                teacher.setBirthday(rs.getDate("birthday").toLocalDate());
                teacher.setContactNumber(rs.getString("contactNumber"));
                teacher.setJobTitle(rs.getString("jobTitle"));

                ArrayList<Subject> courses = getCoursesForTeacher(teacher.getTeacherID(), conn);
                teacher.setTaughtSubjects(courses);

                teacher.setSalary(rs.getFloat("salary"));
                teacher.setYearsOfExperience(rs.getInt("yearsOfExperience"));

            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Error general: " + e.getMessage());
        }

        return teacher;
    }

    private ArrayList<Subject> getCoursesForTeacher(String teacherId, Connection conn) {

        ArrayList<Subject> courses = new ArrayList<>();

        String query = "SELECT subjects.subject_id, subject_name, credits FROM TeacherSubjects "
                    + "JOIN Subjects ON TeacherSubjects.subject_id = Subjects.subject_id "
                    + "WHERE teacher_id = ?";

        try {

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, teacherId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Subject course = new Subject();
                course.setSubjectID(rs.getString("subject_id"));
                course.setSubjectName(rs.getString("subject_name"));
                course.setCredits(rs.getString("credits"));
                courses.add(course);
            }

            } catch (SQLException e) {
            System.out.println("Error general: " + e.getMessage());
        }

        return courses;
    }

}
