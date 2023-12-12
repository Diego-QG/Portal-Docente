package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UConnection {

    private static Connection conn=null;

    //==========================================================================
    public static Connection getConnection() throws SQLException {
        /*
        String driver = "com.mysql.cj.jdbc.Driver";
        String database = "Colegio";
        String hostname = "localhost";
        String port = "3306";
        String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database;
        String username = "root";
        String password = "";
        */
        String driver = "com.mysql.cj.jdbc.Driver";
        String database = "sql10669702";
        String hostname = "sql10.freesqldatabase.com";
        String port = "3306";
        String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database;
        String username = "sql10669702";
        String password = "bU72KpfQk5";
        try {
            Class.forName(driver);
            conn= DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error al conectar a la base de datos", e);
        }
        return conn;
    }

}
