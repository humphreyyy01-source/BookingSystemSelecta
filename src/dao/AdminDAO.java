package dao;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    
    public boolean loginAdmin(String username, String password) {
        boolean isValid = false;
        // SQL query to check if the admin exists in the database
        String query = "SELECT * FROM admins WHERE username = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            // Set the inputs securely to prevent SQL injection
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            // If rs.next() is true, it means a matching record was found
            if (rs.next()) {
                isValid = true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }
}