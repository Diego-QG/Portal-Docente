package Forms.Views;

import Connection.UConnection;
import Entities.Teacher;
import Forms.Login;
import Forms.SignUp;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static Validation.BirthdayVerifier.verifyBirthday;
import static Validation.GenderVerifier.verifyGender;
import static Validation.NameVerifier.verifyName;
import static Validation.ContactNumberVerifier.verifyContactNumber;
import static Validation.JobTitleVerifier.verifyJobTitle;
import static Validation.SalaryVerifier.verifySalary;
import static Validation.YearsOfExperienceVerifier.verifyYearsOfExperience;

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
    private JPanel jplBirthday;
    private JPanel jpoBirthday;
    private JPanel jplContactNumber;
    private JPanel jptfContactNumber;
    private JPanel jplJobTitle;
    private JPanel jptfJobTitle;
    private JPanel jplSalary;
    private JPanel jptfSalary;
    private JPanel jplYearsOfExperience;
    private JPanel jptfYearsOfExperience;
    private JPanel jpMonth;
    private JPanel jpYear;
    private JPanel jpImage;
    private JTextField jtfContactNumber;
    private JTextField jtfJobTitle;
    private JTextField jtfSalary;
    private JTextField jtfYearsOfExperience;
    private JComboBox jcboxMonth;
    private JComboBox jcboxYear;
    private Teacher loggedTeacher = new Teacher();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");


    public ViewProfile(Teacher loggedTeacher) {
        this.loggedTeacher = loggedTeacher;
        add(jpViewProfile);
        setSize(750, 670);
        setLocation(0, 0);
        initStyles();

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(jrbtnMale);
        genderGroup.add(jrbtnFemale);
        genderGroup.add(jrbtnNonBinary);

        initForm();

        jbtnAcceptChanges.addActionListener(evt -> updateData());

    }

    public void initStyles() {

        configureComponentStyle(jpFirstname, jlFirsname);
        configureComponentStyle(jpLastname, jlLastname);
        configureComponentStyle(jpGender, jlGender);
        configureComponentStyle(jpBirthday, jlBirthday);
        configureComponentStyle(jpContactNumber, jlContactNumber);
        configureComponentStyle(jpJobTitle, jlJobTitle);
        configureComponentStyle(jpSalary, jlSalary);
        configureComponentStyle(jpYearsOfExperience, jlYearsOfExperience);

        applyCursorEffect(jbtnAcceptChanges);

    }

    private void configureComponentStyle(JPanel panel, JLabel label) {
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        label.putClientProperty("FlatLaf.style", "font: 16 $light.font");
        label.setForeground(Color.BLACK);
    }

    private void applyCursorEffect(JButton button) {
        button.setBackground(Color.decode("#E9D5DA"));
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

    public void initForm() {
        jtfFirstname.setText(loggedTeacher.getFirstName());

        jtfLastname.setText(loggedTeacher.getLastName());

        if ("Male".equals(loggedTeacher.getGender())) {
            jrbtnMale.setSelected(true);
        }
        if ("Female".equals(loggedTeacher.getGender())) {
            jrbtnFemale.setSelected(true);
        }
        if ("Non-Binary".equals(loggedTeacher.getGender())) {
            jrbtnNonBinary.setSelected(true);
        }

        int day = loggedTeacher.getBirthday().getDayOfMonth();
        int month = loggedTeacher.getBirthday().getMonthValue();
        int year = loggedTeacher.getBirthday().getYear();
        jcboxDay.setSelectedIndex(day-1);
        jcboxMonth.setSelectedIndex(month-1);
        jcboxYear.setSelectedIndex(2004-year);

        jtfContactNumber.setText(loggedTeacher.getContactNumber());

        jtfJobTitle.setText(loggedTeacher.getJobTitle());

        jtfSalary.setText(String.valueOf(loggedTeacher.getSalary()));

        jtfYearsOfExperience.setText(String.valueOf(loggedTeacher.getYearsOfExperience()));
    }

    public void updateData() {
        try (Connection conn = UConnection.getConnection()) {

            String firstName = jtfFirstname.getText();
            String lastName = jtfLastname.getText();
            String gender = getSelectedGender(jrbtnMale, jrbtnFemale, jrbtnNonBinary);
            Object day = jcboxDay.getSelectedItem();
            Object month = jcboxMonth.getSelectedItem();
            Object year = jcboxYear.getSelectedItem();
            String contactNumber = jtfContactNumber.getText();
            String jobTitle = jtfJobTitle.getText();
            String salary = jtfSalary.getText();
            String yearsOfExperience = jtfYearsOfExperience.getText();

            String errors = verifyData(firstName, lastName, gender, day, month, year, contactNumber, jobTitle, salary, yearsOfExperience);

            if (!errors.isEmpty()) {
                JOptionPane.showMessageDialog(ViewProfile.this,
                        "Invalid data: " + errors,
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);
            } else {

                updateObjectcTeacher();


                String sql = "UPDATE Teacher SET firstName = ?, lastName = ?, gender = ?, birthday = ?, contactNumber = ?, jobTitle = ?, salary = ?, yearsOfExperience = ? WHERE teacher_id = ?";

                Statement stmt = conn.createStatement();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, loggedTeacher.getFirstName());
                pstmt.setString(2, loggedTeacher.getLastName());
                pstmt.setString(3, loggedTeacher.getGender());
                pstmt.setDate(4, java.sql.Date.valueOf(loggedTeacher.getBirthday()));
                pstmt.setString(5, loggedTeacher.getContactNumber());
                pstmt.setString(6, loggedTeacher.getJobTitle());
                pstmt.setFloat(7, loggedTeacher.getSalary());
                pstmt.setInt(8, loggedTeacher.getYearsOfExperience());
                pstmt.setInt(9, Integer.parseInt(loggedTeacher.getTeacherID()));
                pstmt.executeUpdate();
                stmt.close();

                JOptionPane.showMessageDialog(ViewProfile.this, "Teacher info updated correctly");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(ViewProfile.this, "Error updating teacher in database: " + e.getMessage());
        }
    }

    private String getSelectedGender(JRadioButton... buttons) {
        for (JRadioButton button : buttons) {
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    public static String verifyData(String firstName, String lastName, String gender, Object day, Object month, Object year, String contactNumber, String jobTitle, String salary, String yearsOfExperience) throws Exception {
        String errors = "";

        try {
            verifyName(firstName);
        } catch (Exception e) {
            errors += "\nFirstname: " + e.getMessage();
        }

        try {
            verifyName(lastName);
        } catch (Exception e) {
            errors += "\nLastname: " + e.getMessage();
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

        try {
            verifyContactNumber(contactNumber);
        } catch (Exception e) {
            errors += "\nContact number: " + e.getMessage();
        }

        try {
            verifyJobTitle(jobTitle);
        } catch (Exception e) {
            errors += "\nJob title: " + e.getMessage();
        }

        try {
            verifySalary(salary);
        } catch (Exception e) {
            errors += "\nSalary: " + e.getMessage();
        }

        try {
            verifyYearsOfExperience(yearsOfExperience);
        } catch (Exception e) {
            errors += "\nYears of experience: " + e.getMessage();
        }

        return errors;
    }

    private void updateObjectcTeacher() {

        String firstName = jtfFirstname.getText().trim();
        String lastName = jtfLastname.getText().trim();
        String gender = getSelectedGender(jrbtnMale, jrbtnFemale, jrbtnNonBinary);
        String day = Objects.requireNonNull(jcboxDay.getSelectedItem()).toString();
        String month = Objects.requireNonNull(jcboxMonth.getSelectedItem()).toString();
        String year = Objects.requireNonNull(jcboxYear.getSelectedItem()).toString();
        String dateString = day + "-" + month + "-" + year;
        LocalDate birthday = LocalDate.parse(dateString, formatter);
        String contactNumber = jtfContactNumber.getText().isEmpty() ? null : jtfContactNumber.getText().trim();
        String jobTitle = jtfJobTitle.getText().isEmpty() ? null : jtfJobTitle.getText().trim();
        float salary;
        try {
            salary = Float.parseFloat(jtfSalary.getText().trim());
        } catch (NumberFormatException e) {
            salary = 0.0f;
        }
        int yearsOfExperience;
        try {
            yearsOfExperience = Integer.parseInt(jtfYearsOfExperience.getText().trim());
        } catch (NumberFormatException e) {
            yearsOfExperience = 0;
        }

        loggedTeacher.setFirstName(firstName);
        loggedTeacher.setLastName(lastName);
        loggedTeacher.setGender(gender);
        loggedTeacher.setBirthday(birthday);
        loggedTeacher.setContactNumber(contactNumber);
        loggedTeacher.setJobTitle(jobTitle);
        loggedTeacher.setSalary(salary);
        loggedTeacher.setYearsOfExperience(yearsOfExperience);

    }

}
