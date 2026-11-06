package config;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            System.out.println("SUCCESS! Database is connected.");
        } else {
            System.out.println("FAILED! Check if XAMPP MySQL is running.");
        }
    }
}