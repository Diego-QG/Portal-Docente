package Forms;

import Connection.UConnection;
import Entities.Alumno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDetails extends JFrame{
    private JPanel jpStudentDetails;
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
    private JPanel jpCity;
    private JPanel jpPostalCodeAge;
    private JPanel jpBtnConfirmChanges;
    private JButton btnConfirmChanges;
    private JPanel jpEstate;
    private JPanel jpPersonalAddress;
    private JTextField jtfCity;
    private JTextField jtfPhone;
    private JPanel jpPostalCode;
    private JPanel jpAge;
    private JTextField jtfPostalCode;
    private JTextField jtfEstate;
    private JTextField jtfPersonalAddress;
    private JTextField jtfAge;
    private int xMouse, yMouse;
    private Alumno alumno = new Alumno();
    private Connection conn;

    public StudentDetails(Alumno alumno, Connection conn) {
        super("Sign Up");
        setContentPane(jpStudentDetails);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setLocationRelativeTo(null);
        setUndecorated(true);
        Shape shape = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 25, 25);
        this.setShape(shape);

        this.alumno = alumno;
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

        initForm();

        btnConfirmChanges.addActionListener(evt -> updateData());

        setVisible(true);

    }

    private void initForm() {
        jtfFirstName.setText(alumno.getNombre());
        jtfLastName.setText(alumno.getApellido());
        jtfEmail.setText(alumno.getCorreoElectronico());
        jtfPhone.setText(alumno.getTelefono());
        jtfCity.setText(alumno.getCiudad());
        jtfPostalCode.setText(alumno.getCodigoPostal());
        jtfAge.setText(String.valueOf(alumno.getEdad()));
        jtfEstate.setText(alumno.getEstado());
        jtfPersonalAddress.setText(alumno.getDireccion());
    }

    private void updateData() {
        alumno.setNombre(jtfFirstName.getText());
        alumno.setApellido(jtfLastName.getText());
        alumno.setCorreoElectronico(jtfEmail.getText());
        alumno.setTelefono(jtfPhone.getText());
        alumno.setCiudad(jtfCity.getText());
        alumno.setCodigoPostal(jtfPostalCode.getText());
        alumno.setEdad(Integer.parseInt(jtfAge.getText()));
        alumno.setEstado(jtfEstate.getText());
        alumno.setDireccion(jtfPersonalAddress.getText());

        String sql = "UPDATE Student SET Firstname = ?, Lastname = ?, Age = ?, Personal_address = ?, City = ?, Estate = ?, Postal_code = ?, Phone = ?, Email = ? WHERE Student_ID = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, alumno.getNombre());
            pstmt.setString(2, alumno.getApellido());
            pstmt.setInt(3, alumno.getEdad());
            pstmt.setString(4, alumno.getDireccion());
            pstmt.setString(5, alumno.getCiudad());
            pstmt.setString(6, alumno.getEstado());
            pstmt.setString(7, alumno.getCodigoPostal());
            pstmt.setString(8, alumno.getTelefono());
            pstmt.setString(9, alumno.getCorreoElectronico());
            pstmt.setInt(10, Integer.parseInt(alumno.getAlumno_id()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
    }

}
