package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Fariha on 12/2/2015.
 */
public class DBConnection {

    private static Connection conn;
    private static String url = "jdbc:mysql://localhost/java_term_project";
    private static String user = "root";
    private static String pass = "hcc";

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: " + cnfe.getMessage());
        } catch (InstantiationException ie) {
            System.err.println("Error: " + ie.getMessage());
        } catch (IllegalAccessException iae) {
            System.err.println("Error: " + iae.getMessage());
        }

        conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (conn != null && !conn.isClosed())
            return conn;
        connect();
        return conn;

    }
}