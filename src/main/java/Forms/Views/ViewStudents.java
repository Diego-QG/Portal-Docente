package Forms.Views;

import Connection.UConnection;
import Entities.Alumno;
import Entities.Teacher;
import Forms.AddStudent;
import Forms.Login;
import Forms.StudentDetails;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ViewStudents extends JPanel{
    private JPanel jpViewStudents;
    private JPanel jpBackground;
    private JPanel jpHeader;
    private JPanel jpSearchDelete;
    private JLabel jlSeDeStudentID;
    private JLabel jlHereStudents;
    private JPanel jpParameters;
    private JTextField jtfPutStudentID;
    private JButton jbtnSearch;
    private JPanel jpOrderBy;
    private JPanel jpWhere;
    private JPanel jpOrWheBtns;
    private JPanel jpButtons;
    private JLabel jlOrderBy;
    private JButton jbtnShow;
    private JButton jbtnAddStudent;
    private JLabel jlWhere;
    private JComboBox jcboxOrderBy;
    private JLabel jlIn;
    private JButton jbtnDelete;
    private JComboBox jcboxIn;
    private JComboBox jcboxWhere;
    private JComboBox jcboxWhereConditions;
    private JTextField jtfCondition;
    private JPanel jpTable;
    private JTable jtStudents;
    private JScrollPane jspStudents;
    private DefaultTableModel tableModel;
    private Teacher loggedTeacher = new Teacher();
    private Connection conn;
    private ArrayList<Alumno> studentList;

    public ViewStudents(Teacher loggedTeacher) {
        this.loggedTeacher = loggedTeacher;
        add(jpViewStudents);
        //setSize(737, 660);
        setSize(750, 670);
        setLocation(0, 0);
        initStyles();
        try{
            conn = UConnection.getConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }

        studentList = createListStudents();

        initTable();

        jbtnSearch.addActionListener(evt -> selectStudent());

        jbtnShow.addActionListener(evt -> showStudentsInOrder());

        jbtnDelete.addActionListener(evt -> deleteStudent());

        jbtnAddStudent.addActionListener(evt -> addNewStudent());

    }

    public void initStyles() {

        configureComponentStyle(jpSearchDelete, jlSeDeStudentID);
        jlHereStudents.putClientProperty("FlatLaf.style", "font: 16 $light.font");
        jlOrderBy.putClientProperty("FlatLaf.style", "font: 16 $light.font");
        jlIn.putClientProperty("FlatLaf.style", "font: 16 $light.font");
        jlWhere.putClientProperty("FlatLaf.style", "font: 16 $light.font");


        applyCursorEffect(jbtnSearch);
        applyCursorEffect(jbtnDelete);
        applyCursorEffect(jbtnShow);
        applyCursorEffect(jbtnAddStudent);

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

    private ArrayList<Alumno> createListStudents() {
        studentList = new ArrayList<>();
        String sql = "SELECT Student_ID, Firstname, Lastname, Age, Phone FROM Student";

        try{

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){

                String studentID = rs.getString("Student_ID");
                String firstName = rs.getString("Firstname");
                String lastName = rs.getString("Lastname");
                int age = rs.getInt("Age");
                String phone = rs.getString("Phone");

                Alumno student = new Alumno(studentID, firstName, lastName, age, phone);
                studentList.add(student);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    private void initTable() {
        String[] columnNames = {"StudentID", "Firstname", "Lastname", "Age", "Phone"};
        tableModel = new DefaultTableModel(0, 5);
        tableModel.setColumnIdentifiers(columnNames);
        jtStudents.setModel(tableModel);

        for (Alumno student : studentList) {
            Object[] rowData = {
                    student.getAlumno_id(),
                    student.getNombre(),
                    student.getApellido(),
                    student.getEdad(),
                    student.getTelefono()
            };
            tableModel.addRow(rowData);
        }

    }

    private void selectStudent() {
        String ID = jtfPutStudentID.getText();
        String sql = "SELECT * FROM Student WHERE Student_ID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String studentID = rs.getString("Student_ID");
                String firstName = rs.getString("Firstname");
                String lastName = rs.getString("Lastname");
                int age = rs.getInt("Age");
                String personal_Address = rs.getString("Personal_address");
                String city = rs.getString("City");
                String estate = rs.getString("Estate");
                String postalCode = rs.getString("Postal_code");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                Alumno student = new Alumno(studentID, firstName, lastName, age, personal_Address, city, estate, postalCode, phone, email);
                new StudentDetails(student, conn);
            } else {
                JOptionPane.showMessageDialog(null, "No student found with ID: " + ID, "Student Not Found", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showStudentsInOrder() {

        int orderByIndex = jcboxOrderBy.getSelectedIndex();
        int inIndex = jcboxIn.getSelectedIndex();

        Comparator<Alumno> comparator = null;

        switch (orderByIndex) {
            case 0:
                comparator = (student1, student2) -> {
                    if (inIndex == 0) {
                        return Integer.compare(Integer.parseInt(student1.getAlumno_id()), Integer.parseInt(student2.getAlumno_id()));
                    } else {
                        return Integer.compare(Integer.parseInt(student2.getAlumno_id()), Integer.parseInt(student1.getAlumno_id()));
                    }
                };
                break;

            case 1:
                comparator = (student1, student2) -> {
                    if (inIndex == 0) {
                        return student1.getNombre().compareTo(student2.getNombre());
                    } else {
                        return student2.getNombre().compareTo(student1.getNombre());
                    }
                };
                break;

            case 2:
                comparator = (student1, student2) -> {
                    if (inIndex == 0) {
                        return student1.getApellido().compareTo(student2.getApellido());
                    } else {
                        return student2.getApellido().compareTo(student1.getApellido());
                    }
                };
                break;

            case 3:
                comparator = (student1, student2) -> {
                    if (inIndex == 0) {
                        return Integer.compare(student1.getEdad(), student2.getEdad());
                    } else {
                        return Integer.compare(student2.getEdad(), student1.getEdad());
                    }
                };
                break;
        }

        List<Alumno> sortedStudentList = studentList.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        sortedStudentList = applyFilter(sortedStudentList);

        clearTable();

        for (Alumno student : sortedStudentList) {
            Object[] rowData = {
                    student.getAlumno_id(),
                    student.getNombre(),
                    student.getApellido(),
                    student.getEdad(),
                    student.getTelefono()
            };
            tableModel.addRow(rowData);
        }

    }

    private List<Alumno> applyFilter(List<Alumno> students) {

        int whereIndex = jcboxWhere.getSelectedIndex();
        int conditionIndex = jcboxWhereConditions.getSelectedIndex();
        String condition = jtfCondition.getText();

        return switch (whereIndex) {
            case 0 -> applyConditionToStudentId(students, conditionIndex, condition);
            case 1 -> applyConditionToFirstname(students, conditionIndex, condition);
            case 2 -> applyConditionToLastname(students, conditionIndex, condition);
            case 3 -> applyConditionToAge(students, conditionIndex, condition);
            default -> students;
        };
    }

    private List<Alumno> applyConditionToStudentId(List<Alumno> students, int conditionIndex, String condition) {
        return switch (conditionIndex) {
            case 0 -> students.stream().filter(student -> student.getAlumno_id().equals(condition)).collect(Collectors.toList());
            case 1 -> students.stream().filter(student -> !student.getAlumno_id().equals(condition)).collect(Collectors.toList());
            case 2 -> students.stream().filter(student -> student.getAlumno_id().compareTo(condition) > 0).collect(Collectors.toList());
            case 3 -> students.stream().filter(student -> student.getAlumno_id().compareTo(condition) >= 0).collect(Collectors.toList());
            case 4 -> students.stream().filter(student -> student.getAlumno_id().compareTo(condition) < 0).collect(Collectors.toList());
            case 5 -> students.stream().filter(student -> student.getAlumno_id().compareTo(condition) <= 0).collect(Collectors.toList());
            case 6 -> students.stream().filter(student -> student.getAlumno_id().startsWith(condition)).collect(Collectors.toList());
            case 7 -> students.stream().filter(student -> student.getAlumno_id().endsWith(condition)).collect(Collectors.toList());
            case 8 -> students.stream().filter(student -> student.getAlumno_id().contains(condition)).collect(Collectors.toList());
            default -> students;
        };
    }

    private List<Alumno> applyConditionToFirstname(List<Alumno> students, int conditionIndex, String condition) {
        return switch (conditionIndex) {
            case 0 -> students.stream().filter(student -> student.getNombre().equals(condition)).collect(Collectors.toList());
            case 1 -> students.stream().filter(student -> !student.getNombre().equals(condition)).collect(Collectors.toList());
            case 2 -> students.stream().filter(student -> student.getNombre().compareTo(condition) > 0).collect(Collectors.toList());
            case 3 -> students.stream().filter(student -> student.getNombre().compareTo(condition) >= 0).collect(Collectors.toList());
            case 4 -> students.stream().filter(student -> student.getNombre().compareTo(condition) < 0).collect(Collectors.toList());
            case 5 -> students.stream().filter(student -> student.getNombre().compareTo(condition) <= 0).collect(Collectors.toList());
            case 6 -> students.stream().filter(student -> student.getNombre().startsWith(condition)).collect(Collectors.toList());
            case 7 -> students.stream().filter(student -> student.getNombre().endsWith(condition)).collect(Collectors.toList());
            case 8 -> students.stream().filter(student -> student.getNombre().contains(condition)).collect(Collectors.toList());
            default -> students;
        };
    }

    private List<Alumno> applyConditionToLastname(List<Alumno> students, int conditionIndex, String condition) {
        return switch (conditionIndex) {
            case 0 -> students.stream().filter(student -> student.getApellido().equals(condition)).collect(Collectors.toList());
            case 1 -> students.stream().filter(student -> !student.getApellido().equals(condition)).collect(Collectors.toList());
            case 2 -> students.stream().filter(student -> student.getApellido().compareTo(condition) > 0).collect(Collectors.toList());
            case 3 -> students.stream().filter(student -> student.getApellido().compareTo(condition) >= 0).collect(Collectors.toList());
            case 4 -> students.stream().filter(student -> student.getApellido().compareTo(condition) < 0).collect(Collectors.toList());
            case 5 -> students.stream().filter(student -> student.getApellido().compareTo(condition) <= 0).collect(Collectors.toList());
            case 6 -> students.stream().filter(student -> student.getApellido().startsWith(condition)).collect(Collectors.toList());
            case 7 -> students.stream().filter(student -> student.getApellido().endsWith(condition)).collect(Collectors.toList());
            case 8 -> students.stream().filter(student -> student.getApellido().contains(condition)).collect(Collectors.toList());
            default -> students;
        };
    }

    private List<Alumno> applyConditionToAge(List<Alumno> students, int conditionIndex, String condition) {
        return switch (conditionIndex) {
            case 0 -> students.stream().filter(student -> student.getEdad() == Integer.parseInt(condition)).collect(Collectors.toList());
            case 1 -> students.stream().filter(student -> student.getEdad() != Integer.parseInt(condition)).collect(Collectors.toList());
            case 2 -> students.stream().filter(student -> student.getEdad() > Integer.parseInt(condition)).collect(Collectors.toList());
            case 3 -> students.stream().filter(student -> student.getEdad() >= Integer.parseInt(condition)).collect(Collectors.toList());
            case 4 -> students.stream().filter(student -> student.getEdad() < Integer.parseInt(condition)).collect(Collectors.toList());
            case 5 -> students.stream().filter(student -> student.getEdad() <= Integer.parseInt(condition)).collect(Collectors.toList());
            default -> students;
        };
    }

    private void clearTable() {
        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }
    }

    private void deleteStudent() {
        String ID = jtfPutStudentID.getText();
        String sql = "DELETE FROM Student WHERE Student_ID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ID);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Student deleted");
            } else {
                JOptionPane.showMessageDialog(null, "No student found with ID: " + ID, "Student Not Found", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addNewStudent() {
        new AddStudent(conn);
    }

}
