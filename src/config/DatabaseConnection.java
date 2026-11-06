package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    
    // XAMPP Default Database Credentials
    private static final String URL = "jdbc:mysql://localhost:3306/selecta_booking_db";
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Load the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the connection to selecta_booking_db
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Database Error: MySQL Driver not found. Please add the Connector/J library.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: Connection Failed. Is XAMPP MySQL running?", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return conn;
    }
}