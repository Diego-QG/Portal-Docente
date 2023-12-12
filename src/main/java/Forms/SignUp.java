package Forms;

import Connection.*;
import Entities.*;

import static Validation.NameVerifier.verifyName;
import static Validation.NameVerifier.formatName;
import static Validation.EmailVerifier.verifyEmail;
import static Validation.PasswordVerifier.verifyPassword;
import static Validation.SPasswordVerifier.verifySPassword;
import static Validation.GenderVerifier.verifyGender;
import static Validation.BirthdayVerifier.verifyBirthday;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class SignUp extends JFrame{
    private JPanel jpSignUp02;
    private JPanel jpHeader;
    private JLabel jlX;
    private JPanel jpPrincipal;
    private JPanel jpLogo;
    private JPanel jpFormSignUp;
    private JLabel jlLoginSignUp;
    private JPanel jpRegister;
    private JPanel jpToLogin;
    private JButton btnToLogin;
    private JPanel jpFullName;
    private JPanel jpEmail;
    private JPanel jpPassword;
    private JPanel jpSPassword;
    private JPanel jpGender;
    private JPanel jpDateOfBirth;
    private JPanel jpFirstName;
    private JPanel jpLastName;
    private JTextField jtfFirstName;
    private JPasswordField jpfPassword;
    private JPanel jpGenders;
    private JRadioButton jrbtnMale;
    private JRadioButton jrbtnFemale;
    private JRadioButton jrbtnNonBinary;
    private JPasswordField jpfSPassword;
    private JTextField jtfEmail;
    private JTextField jtfLastName;
    private JPanel jpBtnLogin;
    private JButton btnSignUp;
    private JComboBox jcboxDay;
    private JPanel jpDay;
    private JPanel jpMonth;
    private JPanel jpYear;
    private JPanel jpCalendar;
    private JLabel jlCalendar;
    private JComboBox jcboxMonth;
    private JComboBox jcboxYear;
    private int xMouse, yMouse;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
    private String firstName;

    public SignUp() {
        super("Sign Up");
        setContentPane(jpSignUp02);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setLocationRelativeTo(null);
        setUndecorated(true);
        Shape shape = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 25, 25);
        this.setShape(shape);
        setIconImage(new ImageIcon("src/main/resources/image.signupx128.png").getImage());

        SetImageLabel(jlLoginSignUp, "src/main/resources/image.signupx128.png", 64, 64);
        SetImageLabel(jlCalendar, "src/main/resources/image.calendarx128.png", 40, 40);

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

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(jrbtnMale);
        genderGroup.add(jrbtnFemale);
        genderGroup.add(jrbtnNonBinary);

        btnToLogin.addActionListener(evt -> {
            new Login();
            dispose();
        });

        btnSignUp.addActionListener(evt -> registerTeacher());

        setVisible(true);
    }

    public void registerTeacher() {

        try (Connection conn = UConnection.getConnection()) {

            String firstName = jtfFirstName.getText();
            String lastName = jtfLastName.getText();
            String email = jtfEmail.getText();
            char[] passwordChars = jpfPassword.getPassword();
            char[] sPasswordChars = jpfSPassword.getPassword();
            String gender = getSelectedGender(jrbtnMale, jrbtnFemale, jrbtnNonBinary);
            Object day = jcboxDay.getSelectedItem();
            Object month = jcboxMonth.getSelectedItem();
            Object year = jcboxYear.getSelectedItem();

            String errors = verifyData(firstName, lastName, email, passwordChars, sPasswordChars, gender, day, month, year);

            if (!errors.isEmpty()) {
                JOptionPane.showMessageDialog(SignUp.this,
                        "Invalid data: " + errors,
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);
            } else {

                Teacher newTeacher = getNewTeacher();

                String sql = "INSERT INTO Teacher (email, password, firstName, lastName, gender, birthday) VALUES (?, ?, ?, ?, ?, ?)";

                Statement stmt = conn.createStatement();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, newTeacher.getEmail());
                pstmt.setString(2, newTeacher.getPassword());
                pstmt.setString(3, newTeacher.getFirstName());
                pstmt.setString(4, newTeacher.getLastName());
                pstmt.setString(5, newTeacher.getGender());
                pstmt.setDate(6, java.sql.Date.valueOf(newTeacher.getBirthday()));
                pstmt.executeUpdate();
                stmt.close();

                JOptionPane.showMessageDialog(SignUp.this, "Teacher inserted successfully");

                new Login();
                dispose();

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(SignUp.this, "Error inserting teacher into database: " + e.getMessage());
        }

    }

    public static String verifyData(String firstName, String lastName, String email, char[] password, char[] sPassword, String gender, Object day, Object month, Object year) throws Exception {
        String errors = "";

        try {
            verifyName(firstName);
        } catch (Exception e) {
            errors += "\nFirstname: " + e.getMessage();
        }

        try {
            verifyName(lastName);
        } catch (Exception e) {
            errors += "\nLastName: " + e.getMessage();
        }

        try {
            verifyEmail(email);
        } catch (Exception e) {
            errors += "\nEmail: " + e.getMessage();
        }

        try {
            verifyPassword(password);
        } catch (Exception e) {
            errors += "\nPassword: " + e.getMessage();
        }

        try {
            verifySPassword(password, sPassword);
        } catch (Exception e) {
            errors += "\nSecond Password: " + e.getMessage();
        }

        try {
            verifyGender(gender);
        } catch (Exception e) {
            errors += "\nGender: " + e.getMessage();
        }

        try {
            verifyBirthday(day, month, year);
        } catch (Exception e) {
            errors += "\nDay of birth: " + e.getMessage();
        }

        return errors;
    }

    private Teacher getNewTeacher() {

        Teacher newTeacher = new Teacher();

        String firstName = formatName(jtfFirstName.getText());
        String lastName = formatName(jtfLastName.getText());
        String email = jtfEmail.getText();
        char[] passwordChars = jpfPassword.getPassword();
        String password = new String(passwordChars);
        char[] sPasswordChars = jpfSPassword.getPassword();
        String sPassword = new String(sPasswordChars);
        String gender = getSelectedGender(jrbtnMale, jrbtnFemale, jrbtnNonBinary);
        String day = Objects.requireNonNull(jcboxDay.getSelectedItem()).toString();
        String month = Objects.requireNonNull(jcboxMonth.getSelectedItem()).toString();
        String year = Objects.requireNonNull(jcboxYear.getSelectedItem()).toString();
        String dateString = day + "-" + month + "-" + year;
        LocalDate birthday = LocalDate.parse(dateString, formatter);

        newTeacher.setFirstName(firstName);
        newTeacher.setLastName(lastName);
        newTeacher.setEmail(email);
        newTeacher.setPassword(password);
        newTeacher.setGender(gender);
        newTeacher.setBirthday(birthday);

        return newTeacher;
    }

    private void SetImageLabel(JLabel label, String path, int width, int height) {
        ImageIcon image = new ImageIcon(path);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        label.setIcon(icon);
        this.repaint();
    }

    private String getSelectedGender(JRadioButton... buttons) {
        for (JRadioButton button : buttons) {
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null; // Ninguno seleccionado
    }

}
