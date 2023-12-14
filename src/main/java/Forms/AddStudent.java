package Forms;

import Entities.Alumno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStudent extends JFrame{
    private JPanel jpAddStudent;
    private JPanel jpHeader;
    private JLabel jlX;
    private JPanel jpLogo;
    private JLabel jlLoginSignUp;
    private JPanel jpFormSignUp;
    private JPanel jpRegister;
    private JPanel jpFullName;
    private JPanel jpFirstName;
    private JTextField jtfFirstName;
    private JPanel jpLastName;
    private JTextField jtfLastName;
    private JPanel jpEmail;
    private JTextField jtfEmail;
    private JPanel jpPhone;
    private JTextField jtfPhone;
    private JPanel jpCity;
    private JTextField jtfCity;
    private JPanel jpPostalCodeAge;
    private JPanel jpPostalCode;
    private JTextField jtfPostalCode;
    private JPanel jpAge;
    private JTextField jtfAge;
    private JPanel jpEstate;
    private JTextField jtfEstate;
    private JPanel jpPersonalAddress;
    private JTextField jtfPersonalAddress;
    private JPanel jpBtnConfirmChanges;
    private JButton btnConfirmChanges;
    private int xMouse, yMouse;
    private Connection conn;
    private Alumno newStudent;

    public AddStudent(Connection conn) {
        super("Add Student");
        setContentPane(jpAddStudent);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setLocationRelativeTo(null);
        setUndecorated(true);
        Shape shape = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 25, 25);
        this.setShape(shape);

        this.conn = conn;

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

        btnConfirmChanges.addActionListener(evt -> registerStudent());

        setVisible(true);

    }

    private void registerStudent() {
        newStudent = new Alumno();
        String firstName = jtfFirstName.getText();
        String lastName = jtfLastName.getText();
        String email = jtfEmail.getText();
        String phone = jtfPhone.getText();
        String city = jtfCity.getText();
        String postalCode = jtfPostalCode.getText();
        String age = jtfAge.getText();
        String estate = jtfEstate.getText();
        String personalAddress = jtfPersonalAddress.getText();

        newStudent.setNombre(firstName);
        newStudent.setApellido(lastName);
        newStudent.setCorreoElectronico(email);
        newStudent.setTelefono(phone);
        newStudent.setCiudad(city);
        newStudent.setCodigoPostal(postalCode);
        newStudent.setEdad(Integer.parseInt(age));
        newStudent.setEstado(estate);
        newStudent.setDireccion(personalAddress);

        String sql = "INSERT INTO Student (Firstname, Lastname, Age, Personal_address, City, Estate, Postal_code, Phone, Email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newStudent.getNombre());
            pstmt.setString(2, newStudent.getApellido());
            pstmt.setInt(3, newStudent.getEdad());
            pstmt.setString(4, newStudent.getDireccion());
            pstmt.setString(5, newStudent.getCiudad());
            pstmt.setString(6, newStudent.getEstado());
            pstmt.setString(7, newStudent.getCodigoPostal());
            pstmt.setString(8, newStudent.getTelefono());
            pstmt.setString(9, newStudent.getCorreoElectronico());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        JOptionPane.showMessageDialog(null, "Student added successfully!");

    }

}
