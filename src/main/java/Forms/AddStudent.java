package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;

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
    }

}
